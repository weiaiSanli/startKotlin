package shiq.com.common.fastjsonfoctory

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializeConfig
import com.alibaba.fastjson.serializer.SerializerFeature
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter
import java.io.IOException

/**
 * created by shi on 2019/6/12/012
 *
 */
internal class FastJsonRequestBodyConverter<T>(
    private val serializeConfig: SerializeConfig,
    vararg features: SerializerFeature
) :
    Converter<T, RequestBody> {
    private val serializerFeatures = features as Array<SerializerFeature>

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val content: ByteArray = if (serializeConfig != null) {
            if (serializerFeatures != null) {
                JSON.toJSONBytes(value, serializeConfig, *serializerFeatures)
            } else {
                JSON.toJSONBytes(value, serializeConfig)
            }
        } else {
            if (serializerFeatures != null) {
                JSON.toJSONBytes(value, *serializerFeatures)
            } else {
                JSON.toJSONBytes(value)
            }
        }
        return RequestBody.create(MEDIA_TYPE, content)
    }

    companion object {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    }
}
