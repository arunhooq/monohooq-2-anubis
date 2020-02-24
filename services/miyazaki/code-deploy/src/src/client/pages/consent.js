import React from 'react';
import { connect } from 'react-redux';
import ConsentHeader from '../components/consent/consentHeader';
import ConsentContent from '../components/consent/consentContent';
import CTAButton from '../components/shared/CTAButton';
import '../components/detail/watchButton.scss';
import { setInitialProps } from '../util/pages';
import Header from '../components/shared/Header';
import { getMovie } from '../services/detail';

const mapStateToProps = state => ({
  translation: state.translation,
  consent: state.detail.consent
});

class Consent extends React.Component {
  static async getInitialProps(ctx) {
    await setInitialProps(ctx);

    const { store } = ctx;
    const region = store.getState().discover.region;
    const { res } = ctx;
    if (res) {
      res.writeHead(302, { Location: '/' });
      res.end();
    }
    return { region };
  }

  state = {
    checkboxTerms: true,
    checkboxPolicy: true,
    movie: {}
  };

  async componentDidMount() {
    const { consent } = this.props;
    if (consent.movieId) {
      const movieData = await getMovie(consent.movieId);
      this.setState({ movie: movieData.data });
    }
  }

  handleCheck = ({ target }) => {
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    this.setState({ [name]: value });
  };

  render() {
    const { checkboxTerms, checkboxPolicy, movie } = this.state;
    const { translation, consent } = this.props;

    return (
      <React.Fragment>
        <Header showBack />
        <ConsentHeader
          partnerLogo={consent.partnerLogo}
          imgHeader={consent.imgHeader}
        />
        <div className="consent-content-wrapper">
          <ConsentContent
            termsChecked={checkboxTerms}
            policyChecked={checkboxPolicy}
            onChange={this.handleCheck}
          />
          <div className="footer-consent">
            {consent.cta &&
              consent.cta.map((btn, index) => {
                return (
                  <div className="cta-consent-btn" key={index}>
                    <CTAButton
                      url={btn.link}
                      backgroundColor={btn.color}
                      color={btn.fontColor}
                      eventName={btn.eventName}
                      movie={movie}
                      opts={btn.opts}
                    >
                      {translation[btn.label]}
                    </CTAButton>
                  </div>
                );
              })}
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default connect(
  mapStateToProps,
  null
)(Consent);
