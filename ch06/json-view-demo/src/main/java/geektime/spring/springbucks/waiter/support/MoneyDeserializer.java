package geektime.spring.springbucks.waiter.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonComponentModule;

import java.io.IOException;

/**
 * @author xschen
 * @see JacksonAutoConfiguration
 * @see JsonComponentModule // 用于处理 @JsonComponent 注解
 */

@JsonComponent // Spring Boot 通过 @JsonComponent 注册 Json 序列化组件
public class MoneyDeserializer extends StdDeserializer<Money> {
    protected MoneyDeserializer() {
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Money.of(CurrencyUnit.of("CNY"), jsonParser.getDecimalValue());
    }
}
