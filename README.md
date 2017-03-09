# cordova-plugin-famocolaser
Cordova plugin to use the Famoco FX300 laser Motorola SE4500

### Installation
    cordova plugin add https://github.com/Legenyes/cordova-plugin-famocolaser.git

### Methods
 * FamocoLaser.scan()

### FamocoLaser.scan()

    declare var cordova: any;
    declare var window: any;
    
    [...]

    this.platform.ready().then(() => {
        (window).FamocoLaser.scan(this.scanSuccess.bind(this), this.scanError.bind(this), null);
    });
    
#### Parameters
 * scanSuccess: The callback that is passed the current position.
 * scanError: (Optional) The callback that executes if an error occurs.
