package memory.fabricators.snapfit

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class SnapfitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }
}
