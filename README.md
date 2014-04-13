ColorPicker
==================

 **!! This is unofficial repository !!**

This is [ColoPicker](https://github.com/flavienlaurent/colorpicker) library as migrate to Gradle style.


Usage
-----------

``` Groovy
repositories {
    maven { url 'https://raw.github.com/ichigotake/colorpicker/migrate-to-gradle/repository' }
}

dependencies {
    compile 'com.fourmob.colorpicker:colorpicker:1.0.0-3'
}
```


Information
-----------

ref: https://github.com/flavienlaurent/colorpicker


Add Feature
-----------

- Add `ColorPickerDialogIcs` as purge `support-v4` from `ColorPickerDialog`. ( same interface
- Add `ColorPickerDialog#initialize` with `String` title as first arguments.


License
-----------

    Copyright 2013 Flavien Laurent

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

