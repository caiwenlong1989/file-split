import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class FileReadUtil {

    public static void main(String[] args) {
//        String pathname = "C:\\Users\\PC\\Desktop\\logs\\";
//        File dir = new File(pathname);
//        File file = new File(pathname + "bbb.log");
//        try {
//            File[] files = dir.listFiles();
//            String line;
//            String line2;
//            String line3;
//            for (File log : files) {
//                BufferedWriter writer = null;
//                BufferedReader reader = null;
//                try {
//                    writer = new BufferedWriter(new FileWriter(file, true));
//                    reader = new BufferedReader(new FileReader(log));
//                    while ((line = reader.readLine()) != null) {
//                        if (line.contains("POST") && line.contains("loginCode")) {
//                            line2 = reader.readLine();
//                            line3 = reader.readLine();
//                            if (line2.contains("Response") && line3.contains("6666")) {
//                                writer.write(line + "\n");
//                                writer.write(line2 + "\n");
//                                writer.write(line3 + "\n");
//                            }
//                        }
//                    }
//                    writer.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    reader.close();
//                    writer.close();
//                }
//                try {
//                    Thread.sleep(500L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(log.getPath());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String pathname = "C:\\Users\\PC\\Desktop\\logs\\";
//        File file = new File(pathname + "ccc.log");
//        BufferedWriter writer = null;
//        BufferedReader reader = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(file));
//            reader = new BufferedReader(new FileReader(new File(pathname + "aaa.log")));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.contains("TASKYYS")) {
//                    writer.write(line.substring(line.indexOf("TASKYYS"), line.indexOf("&nextStage")) + "\n");
//                }
//            }
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//            }
//            try {
//                writer.close();
//            } catch (IOException e) {
//            }
//        }

//        String pathname = "C:\\Users\\PC\\Desktop\\logs\\";
//        BufferedReader reader = null;
//        BufferedWriter writer = null;
//        
//        Set<String> set = new HashSet<>();
//        try {
//            reader = new BufferedReader(new FileReader(new File("C:\\Users\\PC\\Desktop\\ccc.log")));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                set.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//            }
//        }
//
//        String[] taskIds = set.toArray(new String[] {});
//        File dir = new File(pathname);
//        try {
//            File[] files = dir.listFiles();
//            String line;
//            for (File log : files) {
//                try {
//                    writer = new BufferedWriter(new FileWriter(new File(pathname + "eee.log"), true));
//                    reader = new BufferedReader(new FileReader(log));
//                    while ((line = reader.readLine()) != null) {
//                        for (String taskId : taskIds) {
//                            if (line.contains(taskId) && line.contains("user_mobile=")) {
//                                writer.write(line.substring(0, 19) + "\t" + taskId + "\t" + line.substring(line.indexOf("user_mobile=") + 12, line.indexOf("user_mobile=") + 12 + 11) + "\n");
//                            }
//                            if (line.contains(taskId) && line.contains("user_mobile\":\"")) {
//                                writer.write(line.substring(0, 19) + "\t" + taskId + "\t" + line.substring(line.indexOf("user_mobile\":\"") + 14, line.indexOf("user_mobile\":\"") + 14 + 11) + "\n");
//                            }
//                            if (line.contains(taskId) && line.contains("user_name=")) {
//                                writer.write(line.substring(0, 19) + "\t" + taskId + "\t" + line.substring(line.indexOf("user_name=") + 10, line.indexOf("user_name=") + 10 + 11) + "\n");
//                            }
//                        }
//                    }
//                    writer.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    reader.close();
//                    writer.close();
//                }
//                try {
//                    Thread.sleep(500L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(log.getPath());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        Map<String, String> map = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("C:\\Users\\PC\\Desktop\\eee.log")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] kv = line.split("\\t");
                map.put(kv[1], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
        
        BufferedWriter writer = null;
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        try {
            writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\PC\\Desktop\\fff.log"), true));
            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
                String[] vs = entry.getValue().split("\\t");
                writer.write("insert into lts_apply_author_log"
                        + " select"
                        + " '" + UUID.randomUUID().toString().replaceAll("-","") + "',"
                        + " customer_id,"
                        + " product_id,"
                        + " id,"
                        + " '" + vs[1] + "',"
                        + " '06001',"
                        + " 1,"
                        + " '" + vs[0] + "',"
                        + " '【运营商】申请插入成功'"
                        + " from lts_order"
                        + " where customer_id = (select id from lts_customer where phone = '" + vs[2] + "')"
                        + " and not exists(select id from lts_apply_author_log where report_id = '" + vs[1] + "' and author_type = '06001')"
                        + " order by update_time desc"
                        + " limit 1;" + "\n");
                writer.flush();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
        
//        map.forEach((k, v) -> System.out.println(v));
        
        System.out.println("End.");
    }

}
