import 'package:flutter/material.dart';

class HexColor extends Color {

  HexColor(String hexColor): super(_convertToInt(hexColor));

  static int _convertToInt(String hexColor) {
    hexColor = hexColor.toUpperCase().replaceAll("#", "");
    if (hexColor.length == 6) {
      hexColor = "FF" + hexColor;
    }
    return int.parse(hexColor, radix: 16);
  }
}