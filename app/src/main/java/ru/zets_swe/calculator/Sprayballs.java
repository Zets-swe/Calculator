package ru.zets_swe.calculator;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Sprayballs {
    public List<Ball> getSprayballs() {
        return sprayballs;
    }

    List<Ball> sprayballs = new ArrayList<>();
    Activity resourceProvider;
    private String fileName = "sprayballs.csv";
    private String filePath = "ZetsStorage";
    private File mFile;
    private String mData = "";

    public Sprayballs(Activity activity) {
        this.resourceProvider = activity;
    }


    public void readCSV() throws IOException {
        InputStream inputStream = resourceProvider.getResources().openRawResource(R.raw.sprayballs);

        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("Данные из файла::" + builder.toString());

        mFile = new File(resourceProvider.getExternalFilesDir(null), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(mFile);
            fos.write(builder.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




/*

    public void readCSV() throws IOException {
        //Сохраняем таблицу из ресурсов на карту памяти
        Resources r = resourceProvider.getResources();
        InputStream inputStream = r.openRawResource(R.raw.sprayballs);
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isr);

*/
/*        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader("sprayballs.csv"));
        // считываем построчно*//*

        String line = null;
        Scanner scanner = null;
        int index = 0;

        while ((line = reader.readLine()) != null) {
            Ball ball = new Ball();
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    ball.setType(data);
                else if (index == 1)
                    ball.setFactory(data);
                else if (index == 2)
                    ball.setSeries(data);
                else if (index == 3)
                    ball.setMark(data);
                else if (index == 4)
                    ball.setAngle(Integer.parseInt(data));
                else if (index == 5)
                    ball.setDiameter_min(Double.parseDouble(data));
                else if (index == 6)
                    ball.setDiameter_max(Double.parseDouble(data));
                else if (index == 7)
                    ball.setFlow_min(Double.parseDouble(data));
                else if (index == 8)
                    ball.setFlow_1_bar(Double.parseDouble(data));
                else if (index == 9)
                    ball.setFlow_1_5_bar(Double.parseDouble(data));
                else if (index == 10)
                    ball.setFlow_1_7_bar(Double.parseDouble(data));
                else if (index == 11)
                    ball.setFlow_2_bar(Double.parseDouble(data));
                else if (index == 12)
                    ball.setFlow_2_5_bar(Double.parseDouble(data));
                else if (index == 13)
                    ball.setFlow_3_bar(Double.parseDouble(data));
                else if (index == 14)
                    ball.setFlow_3_4_bar(Double.parseDouble(data));
                else if (index == 15)
                    ball.setFlow_4_bar(Double.parseDouble(data));
                else if (index == 16)
                    ball.setFlow_5_bar(Double.parseDouble(data));
                else if (index == 17)
                    ball.setFlow_6_bar(Double.parseDouble(data));
                else if (index == 18)
                    ball.setFlow_7_bar(Double.parseDouble(data));
                else if (index == 19)
                    ball.setFlow_8_bar(Double.parseDouble(data));
                else if (index == 20)
                    ball.setFlow_8_3_bar(Double.parseDouble(data));
                else if (index == 21)
                    ball.setFlow_9_bar(Double.parseDouble(data));
                else if (index == 22)
                    ball.setFlow_9_7_bar(Double.parseDouble(data));
                else if (index == 23)
                    ball.setFlow_10_bar(Double.parseDouble(data));
                else if (index == 24)
                    ball.setFlow_11_bar(Double.parseDouble(data));
                else if (index == 25)
                    ball.setFlow_12_bar(Double.parseDouble(data));
                else if (index == 26)
                    ball.setFlow_12_4_bar(Double.parseDouble(data));
                else if (index == 27)
                    ball.setFlow_13_8_bar(Double.parseDouble(data));
                else if (index == 28)
                    ball.setFlow_16_bar(Double.parseDouble(data));
                else if (index == 29)
                    ball.setFlow_max(Double.parseDouble(data));
                else if (index == 30)
                    ball.setPressure_min(Double.parseDouble(data));
                else if (index == 31)
                    ball.setPressure_max(Double.parseDouble(data));
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
            sprayballs.add(ball);
        }

        Log.w("Строка из шаров",sprayballs.get(3).getFactory());
        System.out.println(sprayballs.get(3).getFactory());
        //закрываем наш ридер
        reader.close();
    }

*/

}
