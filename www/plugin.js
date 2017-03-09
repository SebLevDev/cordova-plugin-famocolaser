module.exports = {
    scan: function (successCallback, errorCallback, data) {
        cordova.exec(successCallback, errorCallback, "FamocoLaser", "scan", [data]);
    }
};