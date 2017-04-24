package cn.pudding.weichat.file;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

public class FileUtil {

    private static 	Logger log = Logger.getLogger(FileUtil.class);
    public 	final 	static String CONTENT_TYPE = "Content-Type";
    //文件下载
    public 	static 	String media_download_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";


    /**
     * 发起https请求并获取结果
     * @param  access_token 	accessToken
     * @param  mediaId 		媒体ID
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static File downLoadMedia(String filePath,String accessToken,String mediaId)
    {
        String url = media_download_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        return httpRequestToFile(filePath,url, "GET", null) ;
    }

    /**
     * 以http方式发送请求,并将请求响应内容输出到文件
     * @param path    请求路径
     * @param method  请求方法
     * @param body    请求数据
     * @return 返回响应的存储到文件
     */
    public static File httpRequestToFile(String fileName,String path, String method, String body)
    {
        if(fileName==null||path==null||method==null)
        {
            return null;
        }
        File file = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body)
            {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            if(inputStream!=null){
                file = new File(fileName);
            }else{
                return file;
            }

            //写入到文件
            fileOut = new FileOutputStream(file);
            if(fileOut!=null){
                int c = inputStream.read();
                while(c!=-1){
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }
        } catch (Exception e) {
            log.error(e);
        }finally{
            if(conn!=null){
                conn.disconnect();
            }

            /*
             * 必须关闭文件流
             * 否则JDK运行时，文件被占用其他进程无法访问
             */
            try {
                inputStream.close();
                fileOut.close();
            } catch (IOException execption) {
                log.error(execption);
            }
        }
        return file;
    }


    public static void main(String[] args) {
        File f = new File("D:/test.png");
        String appId = "";
        String appSecret = "";

        //下载刚刚上传的图片以id命名
        File t = FileUtil.downLoadMedia("D:/test.png","wkIpGo7M_5w7rFL-sz8l1okMjl5FRkElv6IBUOdm5CpjprpdqhC1UmPto8k2mnwVD320azHse8Ubl5nNfpb60S3XQvgoDRZJWIzCy3X7BGI","mMGHvvUNUTVQwd2H80BpbDoyKeyeTSf89rtvV4lzVASugD_AYpxXAwd_seVdyqL-" );

    }
}
