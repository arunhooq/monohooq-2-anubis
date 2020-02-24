/**
 * If the icon file name is `ic-arrow-down.svg` then the name property
 * should be set to `arrow-down`.
 *
 * The component will assume the files is stored in `src/client/static/img`
 */
const Icon = props => {
  const updatedProps = {
    ...props,
    ...{ src: `/static/img/ic-${props.name}.svg` }
  };
  return <img {...updatedProps} />;
};

export default Icon;
