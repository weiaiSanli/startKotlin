package shiq.com.common.fastjsonfoctory

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.parser.ParserConfig
import okhttp3.ResponseBody
import retrofit2.Converter

import java.io.IOException
import java.lang.reflect.Type

/**
 * created by shi on 2019/1/17/017
 */
internal class FastJsonResponseBodyConverter<T>(
    private val mType: Type, private val config: ParserConfig, private val featureValues: Int,
    vararg features: Feature
) : Converter<ResponseBody, T> {
    private val features: Array<Feature> = features as Array<Feature>

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        try {
            return if (mType === String::class.java) {
                value.string() as T
            } else JSON.parseObject<T>(
                value.string(), mType, config, featureValues,
                *features
            )

        } finally {
            value.close()
        }
    }

    companion object {

        private val EMPTY_SERIALIZER_FEATURES = arrayOfNulls<Feature>(0)
    }
}

