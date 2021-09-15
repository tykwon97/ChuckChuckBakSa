package org.tensorflow.codelabs.objectdetection

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import org.tensorflow.odelabs.objectdetection.R
import org.tensorflow.odelabs.objectdetection.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max
import kotlin.math.min


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "TFLite - ODT"
        const val REQUEST_IMAGE_CAPTURE: Int = 200
        private const val MAX_FONT_SIZE = 96F
    }

    lateinit var binding: ActivityMainBinding
    private val OPEN_GALLERY = 100
    private lateinit var getImageFab: Button
    private lateinit var captureImageFab: Button
    private lateinit var inputImageView: ImageView
    private lateinit var tvPlaceholder: TextView
    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImageFab = findViewById(R.id.GetImageFab)
        captureImageFab = findViewById(R.id.captureImageFab)
        inputImageView = findViewById(R.id.imageView)
        tvPlaceholder = findViewById(R.id.tvPlaceholder)
        captureImageFab.setOnClickListener(this)
        getImageFab.setOnClickListener(this)
        var productName = findViewById<TextView>(R.id.name)

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Send_Image(getCapturedImage())
        } else if(requestCode == OPEN_GALLERY && resultCode == Activity.RESULT_OK){
            var currentImageUrl : Uri? = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUrl)
            Send_Image(bitmap)
        }
    }

    /**
     * onClick(v: View?)
     *      Detect touches on the UI components
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.captureImageFab -> {
                try {
                    //dispatchTakePictureIntent()
                    Shooting_Image()
                } catch (e: ActivityNotFoundException) {
                    Log.e(TAG, e.message.toString())
                }
            }
            R.id.GetImageFab -> {
                try {
                    //openGallery()
                    Upload_Image()
                } catch (e: ActivityNotFoundException) {
                    Log.e(TAG, e.message.toString())
                }
            }
        }
    }

    //새로운 이미지를 선택할때마다 호출
    private fun Run_Model(bitmap: Bitmap) {
        // Step 1: 텐서 이미지 만들기
        //텐서 이미지 = 텐서라이트 모델의 입력 유형 -> 기본적으로 입력 이미지를 가져온다.(비트맨 -> 텐서 이미지)
        val image = TensorImage.fromBitmap(bitmap)

        // Step 2: ObjectDetector 인스턴스 (객체를 만들기 위한 구성 포함)
        val options = ObjectDetector.ObjectDetectorOptions.builder()
                .setMaxResults(1)
                .setScoreThreshold(0.6f)
                .build()

        //물체 감지기 만들기
        val detector = ObjectDetector.createFromFileAndOptions(
                this,
                //"model.tflite", //빨대
                //"model2.tflite", //컵라면, 캔, 빨대, 건전지, 아이스팩
                //"modelV2.tflite", //컵라면, 캔, 빨대, 건전지, 페트병, 종이컵
                //"modelV3.tflite", //컵라면, 캔, 빨대, 건전지, 페트병, 종이컵
                "modelV4_2.tflite", //컵라면, 캔, 빨대, 건전지, 페트병, 종이컵
                //"modelV4.tflite", //컵라면, 캔, 빨대, 건전지, 아이스팩
                options
        )

        // Step 3: 모델에 사진 전송
        val results = detector.detect(image)

        // Step 4: 탐지 결과 출력
        val resultToDisplay = results.map {
            val category = it.categories.first() //정확도가 제일 높은 카테고리 선택

            Log.i("test",category.label.toString())
            val text = "${category.label}, ${category.score.times(100).toInt()}%" //%결과로 출력

            // 객체에 결과 값 전달
            DetectionResult(it.boundingBox, text)
        }

        //탐지 결과 그리기
        val imgWithResult = Inform_Result(bitmap, resultToDisplay)
        runOnUiThread {
            inputImageView.setImageBitmap(imgWithResult)
        }
    }

    /**
     * debugPrint(visionObjects: List<Detection>)
     *      Print the detection result to logcat to examine
     */
    private fun debugPrint(results : List<Detection>) {
        for ((i, obj) in results.withIndex()) {
            val box = obj.boundingBox

            Log.d(TAG, "Detected object: ${i} ")
            Log.d(TAG, "  boundingBox: (${box.left}, ${box.top}) - (${box.right},${box.bottom})")

            for ((j, category) in obj.categories.withIndex()) {
                Log.d(TAG, "    Label $j: ${category.label}")
                val confidence: Int = category.score.times(100).toInt()
                Log.d(TAG, "    Confidence: ${confidence}%")
            }
        }
    }

    /**
     * Send_Image(bitmap: Bitmap)
     *      Set image to view and call object detection
     */
    private fun Send_Image(bitmap: Bitmap) {
        inputImageView.setImageBitmap(bitmap) //사진 화면에 띄우기
        tvPlaceholder.visibility = View.INVISIBLE //분리수거 척척박사 문장 제거

        lifecycleScope.launch(Dispatchers.Default) { Run_Model(bitmap) } //모델에 사진 전송
        //계산 많이 함 ->백그라운드 스레드에서 OD 수행(app ui blocking예방)
        //main thread에서 사용 x
    }

    /**
     * getCapturedImage():
     *      Decodes and crops the captured image from camera.
     */
    private fun getCapturedImage(): Bitmap {
        // Get the dimensions of the View
        val targetW: Int = inputImageView.width
        val targetH: Int = inputImageView.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            BitmapFactory.decodeFile(currentPhotoPath, this)

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = max(1, min(photoW / targetW, photoH / targetH))

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inMutable = true
        }
        val exifInterface = ExifInterface(currentPhotoPath)
        val orientation = exifInterface.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )

        val bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                rotateImage(bitmap, 90f)
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                rotateImage(bitmap, 180f)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                rotateImage(bitmap, 270f)
            }
            else -> {
                bitmap
            }
        }
    }



    /**
     * getSampleImage():
     *      Get image form drawable and convert to bitmap.
     */
    private fun getSampleImage(drawable: Int): Bitmap {
        return BitmapFactory.decodeResource(resources, drawable, BitmapFactory.Options().apply {
            inMutable = true
        })
    }

    /**
     * rotateImage():
     *     Decodes and crops the captured image from camera.
     */
    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }

    /**
     * createImageFile():
     *     Generates a temporary image file for the Camera app to write to.
     */

    @Throws(IOException::class)
    private fun createImageFile(): File { //이미지 파일 생성
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            // 사진 저장
            currentPhotoPath = absolutePath
        }
    }


    private fun Shooting_Image() { //카메라 촬영
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (e: IOException) {
                    Log.e(TAG, e.message.toString())
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "org.tensorflow.codelabs.objectdetection.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun Upload_Image(){ //갤러리에서 사진 가져오기
        val intent:Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,OPEN_GALLERY)
    }

    /**
     * Inform_Result(bitmap: Bitmap, detectionResults: List<DetectionResult>
     *      Draw a box around each objects and show the object's name.
     */
    //입력 이미지 위에 띄울 탐지 결과를 그리기
    private fun Inform_Result(
        bitmap: Bitmap,
        detectionResults: List<DetectionResult>
    ): Bitmap {
        val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(outputBitmap)
        val pen = Paint()
        pen.textAlign = Paint.Align.LEFT

        detectionResults.forEach {
            // 바운딩 박스 그리기
            pen.color = Color.RED
            pen.strokeWidth = 8F
            pen.style = Paint.Style.STROKE
            val box = it.boundingBox
            canvas.drawRect(box, pen)

            //MainActivity에 결과 출력
            Thread {
                runOnUiThread {
                    binding.name.setText(it.text)
                }
            }.start()

            val tagSize = Rect(0, 0, 0, 0)

            pen.style = Paint.Style.FILL_AND_STROKE
            pen.color = Color.YELLOW
            pen.strokeWidth = 2F

            pen.textSize = MAX_FONT_SIZE
            pen.getTextBounds(it.text, 0, it.text.length, tagSize)
            val fontSize: Float = pen.textSize * box.width() / tagSize.width()

            if (fontSize < pen.textSize) pen.textSize = fontSize

            var margin = (box.width() - tagSize.width()) / 2.0F
            if (margin < 0F) margin = 0F
            canvas.drawText(
                it.text, box.left + margin,
                box.top + tagSize.height().times(1F), pen
            )
        }
        return outputBitmap
    }
}

/**
 * DetectionResult
 *      A class to store the visualization info of a detected object.
 */
//시각화를 포함하는 데이터 클래스
data class DetectionResult(val boundingBox: RectF, val text: String)
