package util

import okhttp3.OkHttpClient

class MRVNOkHttpClient : OkHttpClient() {
    companion object {
        val instance = MRVNOkHttpClient()
    }
}
