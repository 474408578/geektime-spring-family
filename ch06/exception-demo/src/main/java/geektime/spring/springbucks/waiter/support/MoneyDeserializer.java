package geektime.spring.springbucks.waiter.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class MoneyDeserializer extends StdDeserializer<Money> {
    protected MoneyDeserializer() {
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        // 可接收 String 类型
        //return Money.of(CurrencyUnit.of("CNY"), Double.valueOf(p.getText()));
        // 接收 Number 类型
        return Money.of(CurrencyUnit.of("CNY"), p.getDecimalValue());
    }
}
