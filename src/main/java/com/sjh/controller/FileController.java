package com.sjh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;


/**
 * 文件上传
 */
@Controller
@ResponseBody
public class FileController {

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "sendImage") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "J://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return "file";
    }

    @RequestMapping("/uploadAudio")
    @ResponseBody
    public void uploadAudio(@RequestParam(value = "file") MultipartFile file) {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            System.out.println("Load fn:" + filename);
            //System.out.println("Load uploadfile:" + fname);
            try {

                String fileurl = "J:\\/" + filename;
                File f = new File(fileurl);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                if (!f.exists()) {
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("上传失败，因为文件是空的.");
        }
    }
}
