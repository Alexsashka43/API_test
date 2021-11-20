import com.google.gson.Gson;
import groovy.lang.IntRange;
import model.IPdto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIp {
    @Test
    public void testIp() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Экземпляр класса Request создается через Builder (см. паттерн проектирования "Строитель")
        Request request = new Request.Builder()
                .url("https://api.ipify.org/?format=json")
                .build();

        // Получение объекта ответа от сервера
        Response response = client.newCall(request).execute();

        // Тело сообщения возвращается методом body объекта Response
        String body = response.body().string();
        Gson gson = new Gson();
        IPdto resp = gson.fromJson(body, IPdto.class);
        assertThat(resp.getIp()).isEqualTo("89.17.49.68");
        assertThat(resp.getIp().split("[.]").length).isEqualTo(4);
        IntRange range = new IntRange(0, 255);
        for (int i = 0; i < 4; i++) {
            assertThat(range.contains(Integer.parseInt(resp.getIp().split("[.]")[i]))).isEqualTo(true);
        }
//        test Класс IP
        IntRange rangeA = new IntRange(0, 127);
        IntRange rangeB = new IntRange(127, 192);
        IntRange rangeC = new IntRange(192, 223);
        int num = Integer.parseInt(resp.getIp().split("[.]")[0]);
        if (rangeA.contains(num)) {
            String result = "A";
            assertThat(result).isEqualTo("A");
            System.out.println("IP belongs to class A");
        } else if (rangeB.contains(num)) {
            String result = "B";
            assertThat(result).isEqualTo("B");
            System.out.println("IP belongs to class B");
        } else if (rangeC.contains(num)) {
            String result = "C";
            assertThat(result).isEqualTo("C");
            System.out.println("IP belongs to class C");
        }
    }
}
