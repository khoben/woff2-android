# WOFF2 Android
![Maven Central](https://img.shields.io/maven-central/v/io.github.khoben.woff2-android/decoder)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Android woff2 typeface decoder

## Installation
```gradle
# Woff2 android typeface converter
implementation 'io.github.khoben.woff2-android:typeface:0.0.2'

# Or single native woff2 decoder
implementation 'io.github.khoben.woff2-android:decoder:0.0.2'
```

## Sample usage

```kotlin
class MainActivity : AppCompatActivity(R.layout.main_layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editText = findViewById<EditText>(R.id.text_edit)
        // using extension
        editText.woff2Typeface(assets, "test.woff2")
        editText.woff2Typeface("path/test.woff2")
        editText.woff2Typeface(File("path/test.woff2"))
        editText.woff2Typeface(ByteArray(...))

        editText.setTypeface(Woff2Typeface.get().createFromFile("path/test.woff2"))
        editText.setTypeface(Woff2Typeface.get().createFromFile(File("path/test.woff2")))
        editText.setTypeface(Woff2Typeface.get().createFromAsset(assets, "test.woff2"))
        editText.setTypeface(Woff2Typeface.get().createFromBytes(ByteArray(...)))
    }
}
```

## Build

1. Setup `woff2` build in [build_ndk.properties](./scripts/build_ndk.properties)

2. Build `woff2` via python script:

    ```bash
    python ./scripts/build_ndk.py
    ```

Make sure you have NDK version 28 and above.

## License

```
Copyright 2022 khoben
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```