/*
 * Copyright (c) 2016-2018 Hunan CNBOT Co., Ltd. All Rights Reserved.
 * FileName: positionformatter.js
 * @author: Clintonfang
 * @date: 18-8-20 上午11:03
 * @version: 1.0
 */

function latitudeFormatter(value, row, index) {
    var degrees = parseInt(value);
    var tmpValue = (value - degrees) * 60;
    var myMinutes = parseInt(tmpValue);
    var seconds = Math.round((tmpValue - myMinutes) * 60000) / 1000;
    var ns = "N";
    if (value < 0) {
        ns = "S"
    }
    return ns + Math.abs(degrees) + "°" + myMinutes + "′" + seconds + "″";
}

function longitudeFormatter(value, row, index) {
    var degrees = parseInt(value);
    var tmpValue = (value - degrees) * 60;
    var myMinutes = parseInt(tmpValue);
    var seconds = Math.round((tmpValue - myMinutes) * 60000) / 1000;
    var ew = "E";
    if (value < 0) {
        ew = "W"
    }
    return ew + Math.abs(degrees) + "°" + myMinutes + "′" + seconds + "″";
}
