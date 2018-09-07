import React, { Component } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import RoundImageView from './RoundImageView';

export default class RoundImageViewTest extends Component {
  render() {
    return (
      <View style={styles.container}>
        <RoundImageView style={{width: 100, height: 100}} name="icon_rectangle" type="circle" />
		<RoundImageView style={{width: 100, height: 100}} name="icon_rectangle" type="round" roundradius={30} />
		<RoundImageView style={{width: 80, height: 100}} name="icon_rectangle" type="oval" roundradius={45} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-around',
  },
});

