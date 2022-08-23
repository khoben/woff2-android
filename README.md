## WOFF2 Android

Android woff2 typeface decoder

### Build
1. Setup `woff2` build in [build_ndk.properties](./scripts/build_ndk.properties)

2. Build `woff2` via python script:

    ```bash
    python ./scripts/build_ndk.py
    ```

### Sample usage

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