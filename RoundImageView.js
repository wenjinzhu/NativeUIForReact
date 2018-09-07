import { PropTypes } from 'prop-types';
import { requireNativeComponent, View } from 'react-native';

const RoundImageView = requireNativeComponent('RoundImageView', {
  propTypes: {
    name: PropTypes.string,
	type: PropTypes.oneOf(['circle', 'round', 'oval']),
	roundradius: PropTypes.number,
    ...View.propTypes // 包含默认的View的属性
  },
});

export default RoundImageView;