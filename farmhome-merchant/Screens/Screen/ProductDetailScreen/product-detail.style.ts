import {StyleSheet} from 'react-native';
import {Colors} from '../../constants/color.constants';
import {FontSize} from '../../constants/fontsize.constants';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Colors.DarkGreen,
  },
  imageProductBackground: {
    backgroundColor: Colors.Black,
    width: '100%',
    height: 200,
    paddingHorizontal: '3%',
    paddingTop: 10,
  },
  imageContainer: {
    marginLeft: '8%',
    width: '80%',
    height: 200,
    marginTop: -120,
  },
  imageWrapper: {
    width: '100%',
    height: '100%',
    borderRadius: 30,
    overflow: 'hidden',
    backgroundColor: 'transparent',
    borderColor: 'white',
    borderWidth: 1,
  },
  productInformation: {
    width: '50%',
    marginTop: 20,
    marginLeft: '10%',
  },
  productName: {
    fontSize: FontSize.Large,
    fontWeight: '800',
  },
  productDescription: {
    fontSize: FontSize.Large,
    fontWeight: '400',
    marginLeft: '5%',
    color: '#FFFFFF90',
  },
  productDate: {
    fontWeight: '400',
    fontSize: FontSize.Small,
    marginLeft: '5%',
    color: '#FFFFFF90',
  },
  farmerInformation: {
    marginTop: 20,
  },
  farmerTitle: {
    fontSize: FontSize.Normal,
    fontWeight: '600',
    marginBottom: 10,
  },
  farmerInfo: {
    fontSize: FontSize.Small,
    fontWeight: '400',
  },
  contentContainer: {
    width: '100%',
    flexDirection: 'row',
    marginTop: 10,
  },
  rightButton: {
    width: '30%',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 20,
  },
  inStockUp: {
    width: '90%',
    backgroundColor: Colors.DarkFuelGreen,
    borderRadius: 80,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 2,
    paddingVertical: 7,
  },
  inStockUpTitle: {
    fontSize: FontSize.NormalSmall,
    fontWeight: '500',
  },
  inStockUpNumber: {
    fontSize: FontSize.NormalSmall,
    fontWeight: '500',
  },
  viewShopContainer: {
    width: '100%',
    marginTop: 30,
    alignItems: 'center',
  },
  viewShopTitle: {
    fontSize: FontSize.Small,
    fontWeight: '400',
    color: Colors.DarkFuelGreen,
  },
  phoneNowTitle: {
    fontSize: FontSize.Small,
    fontWeight: '400',
    color: Colors.DarkFuelGreen,
  },
  buttonContainer: {
    width: '80%',
    alignItems: 'center',
    paddingVertical: 3,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: Colors.DarkFuelGreen,
    marginBottom: 10,
  },
  headerContainer: {},
  imageProductContainer: {},
  iconImage: {},
  bottomSheetContainer: {
    paddingHorizontal: '5%',
    paddingTop: 20,
  },
  bottomTitle: {
    color: Colors.DarkGreen,
    textAlign: 'center',
    marginVertical: 10,
  },
  disable: {
    color: Colors.DarkGreen50,
    borderColor: Colors.DarkFuelGreen50,
  },
  submitButton: {
    width: '30%',
    borderWidth: 1,
    borderColor: Colors.DarkGreen,
    marginTop: 30,
    borderRadius: 20,
    alignSelf: 'center',
  },
  inputTitle: {
    fontSize: FontSize.Normal,
    color: Colors.DarkGreen,
  },
  inputText: {
    borderBottomWidth: 1,
    borderColor: Colors.DarkGreen,
    color: Colors.DarkGreen,
  },
  infoInputContainer: {
    marginVertical: 10,
  },
  addressInput: {
    width: '100%',
    borderRadius: 10,
    borderWidth: 1,
    borderColor: Colors.DarkGreen,
    height: 48,
    justifyContent: 'center',
    paddingLeft: 10,
    marginTop: 10,
  },
  inputAddressInformationContainer: {
    marginTop: 5,
  },
  addressButton: {
    marginTop: 20,
  },
  defaultAddressContainer: {
    borderColor: Colors.DarkGreen,
    borderWidth: 1,
    paddingHorizontal: 10,
    paddingVertical: 5,
    marginTop: 5,
    borderRadius: 10,
  },
});
