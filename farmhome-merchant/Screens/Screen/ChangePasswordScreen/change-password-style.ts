import React from 'react';
import {StyleSheet} from 'react-native';
import {Colors} from '../../constants/color.constants';
import {FontSize} from '../../constants/fontsize.constants';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: '5%',
    paddingVertical: 20,
    //backgroundColor: Colors.DarkGreen,
  },
  changePasswordTitle: {
    marginTop: 15,
    fontSize: FontSize.Large,
    color: Colors.DarkGreen,
  },
  passwordRole: {
    fontSize: FontSize.Normal,
    color: Colors.DarkGreen,
  },
  inputContainer: {
    marginVertical: 20,
  },
  inputTitle: {
    fontSize: FontSize.MediumSmall,
    color: Colors.DarkGreen,
    marginBottom: 10,
  },
  input: {
    flexDirection: 'row',
    borderColor: Colors.DarkGreen,
    borderRadius: 15,
    borderWidth: 1,
  },
  inputText: {
    width: '85%',
    color: Colors.DarkGreen,
  },
  hiddenIcon: {
    width: '15%',
    height: 50,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: '60%',
    height: '60%',
  },
  securityStrength: {
    width: '100%',
    height: 8,
    borderColor: 'grey',
    borderRadius: 10,
    borderWidth: 1,
  },
  securityWeak: {
    width: '33%',
    height: 6,
    borderColor: 'grey',
    borderRadius: 10,
    borderWidth: 1,
    backgroundColor: 'red',
  },
  securityNormal: {
    width: '66%',
    height: 6,
    borderColor: 'grey',
    borderRadius: 10,
    borderWidth: 1,
    backgroundColor: 'yellow',
  },
  securityStrong: {
    width: '100%',
    height: 6,
    borderColor: 'grey',
    borderRadius: 10,
    borderWidth: 1,
    backgroundColor: 'green',
  },
  securityNone: {
    width: '0%',
    height: 6,
    borderColor: 'grey',
    borderRadius: 10,
    borderWidth: 1,
  },
});
