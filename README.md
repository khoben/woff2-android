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
        editText.woff2Typeface(this, assets, "test.woff2")
        editText.woff2Typeface(this, "path/test.woff2")
        editText.woff2Typeface(this, File("path/test.woff2"))

        editText.setTypeface(Woff2Typeface.createFromFile(this, "path/test.woff2"))
        editText.setTypeface(Woff2Typeface.createFromFile(this, File("path/test.woff2")))
        editText.setTypeface(Woff2Typeface.createFromAsset(this, assets, "test.woff2"))
    }
}
```