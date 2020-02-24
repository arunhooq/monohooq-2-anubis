import { shallow } from 'enzyme';
import { movie, row } from '../../../../__mocks__/spotlight';

import {
  CarouselImage,
  CarouselPosterTitle
} from '../../../../../src/client/components/discover/carouselDisplay';
import RowTitle from '../../../../../src/client/components/shared/RowTitle';

const movieWithSpotlight = {
  ...{ movie },
  ...{ spotlight: true, hasLabel: true }
};

const movieWithoutSpotlight = {
  ...{ movie },
  ...{
    spotlight: false,
    hasLabel: true
  }
};

describe('<CarouselImage />', () => {
  it('if spotlight is true, render the movie title', () => {
    const wrapper = shallow(<CarouselImage {...movieWithSpotlight} />);
    expect(
      wrapper.contains(
        <CarouselPosterTitle
          spotlight={true}
          title={movieWithSpotlight.movie.title}
        />
      )
    ).toEqual(true);
  });

  it('if spotlight is true, render the movie title', () => {
    const wrapper = shallow(<CarouselImage {...movieWithoutSpotlight} />);
    expect(
      wrapper.contains(
        <CarouselPosterTitle
          spotlight={true}
          title={movieWithoutSpotlight.movie.title}
        />
      )
    ).toEqual(false);
  });
});

describe('<RowTitle />', () => {
  it('should render RowTitle', () => {
    const mockProps = {
      row: row,
      translation: {
        curation_seeAll: ''
      }
    };
    const wrapper = shallow(<RowTitle {...mockProps} />, {
      disableLifecycleMethods: true
    });
    expect(wrapper.find('.row-title')).toHaveLength(1);
  });
});
