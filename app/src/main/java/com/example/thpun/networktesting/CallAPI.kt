package com.example.thpun.networktesting

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class CallAPI() : AsyncTask<String, String, String>() {

    override fun doInBackground(vararg params: String?): String {
        val url = URL("https://v5sf8pb1e0.execute-api.us-east-2.amazonaws.com/test2")
        val conn = url.openConnection() as HttpURLConnection

        try {
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-api-key", "")
            conn.doInput = true;
            conn.doOutput = true;

            val jsonParam = JSONObject()
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK)
            jsonParam.put("name", "foreground")
            jsonParam.put("time", sdf.format(Calendar.getInstance().time))

            Log.i("JSON", jsonParam.toString())

            val os = DataOutputStream(conn.outputStream)
            os.writeBytes(jsonParam.toString())
            os.flush()
            os.close()

            Log.i("STATUS", conn.responseCode.toString());
            Log.i("MSG", conn.responseMessage);

        } catch (e: Exception) {
            e.printStackTrace();
        } finally {
            conn.disconnect()
        }

        return "CallAPI"
    }
}