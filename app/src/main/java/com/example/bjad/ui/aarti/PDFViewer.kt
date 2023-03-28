package com.example.bjad.ui.aarti

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bjad.databinding.FragmentPDFViewerBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val TAG = "PDFViewer"
class PDFViewer : Fragment() {

    private lateinit var binding:FragmentPDFViewerBinding
    private lateinit var pdfView:PDFView
    private var url:String = ""
    val pageNumber:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentPDFViewerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        pdfView = binding.pdfView
        url = arguments?.getString("url").toString()
        Log.d(TAG, "onViewCreated: $url")
        RetrievePDFfromUrl(requireContext(),pdfView,pageNumber).execute(url)
    }


}

class RetrievePDFfromUrl(
    private val context: Context,
    private val pdfView:PDFView,
    private val pageNumber:Int
) : AsyncTask<String, Void, InputStream>() {

    override fun doInBackground(vararg strings: String): InputStream? {
        // we are using inputstream
        // for getting out PDF.
        var inputStream: InputStream? = null
        try {
            val url = URL(strings[0])
            // below is the step where we are
            // creating our connection.
            val urlConnection = url.openConnection() as HttpsURLConnection
            if (urlConnection.responseCode == 200) {
                // response is success.
                // we are getting input stream from url
                // and storing it in our variable.
                inputStream = BufferedInputStream(urlConnection.inputStream)
            }

        } catch (e: IOException) {
            // this is the method
            // to handle errors.
            e.printStackTrace()
            return null
        }
        return inputStream
    }

    override fun onPostExecute(inputStream: InputStream?) {
        // after the execution of our async
        // task we are loading our pdf in our pdf view.
        pdfView.fromStream(inputStream)
            .defaultPage(pageNumber)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableAnnotationRendering(true)
            .scrollHandle(DefaultScrollHandle(context))
            .load()
    }
}
