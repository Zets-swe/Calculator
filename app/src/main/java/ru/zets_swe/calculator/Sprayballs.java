package ru.zets_swe.calculator;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Sprayballs {
    public List<Ball> getSprayballs() {
        return sprayballs;
    }

    List<Ball> sprayballs = new ArrayList<>();
    Activity resourceProvider;
    private String fileName = "sprayballs.csv";
    InputStream sourceFile;
    File destFile;

    public Sprayballs(Activity activity) {
        this.resourceProvider = activity;
    }

    public void init() {
        this.sourceFile = resourceProvider.getResources().openRawResource(R.raw.sprayballs);
        this.destFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        if (!destFile.exists()) {
            try {
                this.copyCSVfromRaw();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyCSVfromRaw() throws IOException {
        InputStreamReader isr = new InputStreamReader(sourceFile);
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
            sourceFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(destFile);
            fos.write(builder.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readCSV() throws IOException {

        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/" + fileName));
        // считываем построчно
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
            //System.out.println("add ball " + sprayballs.size());
        }
        reader.close();
        //Log.w("Строка из шаров",sprayballs.get(3).getFactory());
        //закрываем наш ридер

    }


    public void copyCSV(File sourceFile, File dFile) throws IOException {
        if (!dFile.exists()) {
            dFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(dFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }


    public List<String> getUniqType() {
        Set<String> uniqItems = new HashSet<String>();
        for (Ball ball : sprayballs) {
            uniqItems.add(ball.getType());
        }
        List<String> arrayUniqItems = new ArrayList<String>();
        ;
        arrayUniqItems.addAll(uniqItems);
        return arrayUniqItems;
    }


    public List<String> getTypeAngles(String type) {
        Set<String> uniqAngles = new HashSet<String>();
        for (Ball ball : sprayballs) {
            if (ball.getType().equals(type)) {
                uniqAngles.add(String.valueOf(ball.getAngle()));
            }

        }
        List<String> arrayUniqItems = new ArrayList<String>();
        arrayUniqItems.addAll(uniqAngles);
        return arrayUniqItems;
    }


    public List<Ball> filterByTADF(String type, String angleS, String diameterS, String flowS) {
        int angle = Integer.parseInt(angleS);
        double diameter = Double.parseDouble(diameterS);
        double flow = Double.parseDouble(flowS);
        List<Ball> filteredBalls = new ArrayList<>();
        filteredBalls.addAll(sprayballs);

        filteredBalls = filterByType(type, filteredBalls);
        filteredBalls = filterByAngle(angle, filteredBalls);
        filteredBalls = filterByDiameter(diameter, filteredBalls);
        filteredBalls = filterByFlow(flow, filteredBalls);

    for (Ball b : filteredBalls) {
        System.out.println(b.toString());
    }
        return filteredBalls;
    }


    private List<Ball> filterByType(String type, List<Ball> filteredBalls) {
        List<Ball> typeFilter = new ArrayList<>();
        for (Ball b : filteredBalls) {
            if (type.equals(b.getType())) {
                typeFilter.add(b);
            }
        }
        return typeFilter;
    }

    private List<Ball> filterByAngle(int angle, List<Ball> filteredBalls) {
        List<Ball> angleFilter = new ArrayList<>();
        for (Ball b : filteredBalls) {
            if (angle == b.getAngle()) {
                angleFilter.add(b);
            }
        }
        return angleFilter;
    }

    private List<Ball> filterByDiameter(double diameter, List<Ball> filteredBalls) {
        List<Ball> diametrFilter = new ArrayList<>();
        for (Ball b : filteredBalls) {
            if ((b.getDiameter_min() <= diameter) && (diameter <= b.getDiameter_max())) {
                diametrFilter.add(b);
            }
        }
        return diametrFilter;
    }

    private List<Ball> filterByFlow(double flow, List<Ball> filteredBalls) {
        List<Ball> flowFilter = new ArrayList<>();
        for (Ball b : filteredBalls) {
            if ((b.getFlow_min() <= flow) && (flow <= b.getFlow_max())) {
                flowFilter.add(b);
            }
        }
        return flowFilter;
    }

}
