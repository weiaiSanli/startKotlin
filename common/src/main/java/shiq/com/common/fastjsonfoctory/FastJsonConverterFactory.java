package shiq.com.common.fastjsonfoctory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 仿照https://github.com/ligboy/retrofit-converter-fastjson 库,由于该库不在更新对kotlin的支持,
 * 自己导入支持kotlin的data数据类重新各个类解决data中没有默认构造函数导致的报错问题
 * created by shi on 2019/1/17/017
 */
public class FastJsonConverterFactory extends Converter.Factory{

    private ParserConfig mParserConfig = ParserConfig.getGlobalInstance();
    private int featureValues = JSON.DEFAULT_PARSER_FEATURE;
    private Feature[] features;

    private SerializeConfig serializeConfig;
    private SerializerFeature[] serializerFeatures = {SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.SkipTransientField,
            SerializerFeature.DisableCircularReferenceDetect};

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    private FastJsonConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type, mParserConfig, featureValues, features);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>(serializeConfig, serializerFeatures);
    }

    public ParserConfig getmParserConfig() {
        return mParserConfig;
    }

    public void setmParserConfig(ParserConfig mParserConfig) {
        this.mParserConfig = mParserConfig;
    }

    public int getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(int featureValues) {
        this.featureValues = featureValues;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }

    public SerializeConfig getSerializeConfig() {
        return serializeConfig;
    }

    public void setSerializeConfig(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
    }

    public SerializerFeature[] getSerializerFeatures() {
        return serializerFeatures;
    }

    public void setSerializerFeatures(SerializerFeature[] serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }
}