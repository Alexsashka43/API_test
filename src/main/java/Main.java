import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;


public class Main {
    public static void main(String[]args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Экземпляр класса Request создается через Builder (см. паттерн проектирования "Строитель")
        Request request = new Request.Builder()
                .url("https://api.ipify.org/?format=json")
                .build();

        // Получение объекта ответа от сервера
        Response response = client.newCall(request).execute();

        // Тело сообщения возвращается методом body объекта Response
        String body = response.body().string();

        System.out.println(body.intern());
    }
}
