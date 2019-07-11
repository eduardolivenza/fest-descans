package com.eolivenza.modules.baseProject.controller.http.rest;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamStreamer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.awt.*;

@Named
public class WebcamController {

    @Autowired
    public  WebcamController(){
        Thread th = new Thread(() -> {
            try {
                Webcam w = Webcam.getDefault();
                w.setViewSize(new Dimension(640, 480));
                new WebcamStreamer(8081, w, 30, true);
                while (true) {
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        th.start();
    }



}
