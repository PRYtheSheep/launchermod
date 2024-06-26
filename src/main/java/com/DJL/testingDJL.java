package com.DJL;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.engine.Engine;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.transform.ToTensor;
import ai.djl.modality.cv.translator.YoloV8Translator;
import ai.djl.modality.cv.translator.YoloV8TranslatorFactory;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.Pipeline;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class testingDJL {

    public static void main(String[] args) throws ModelNotFoundException, MalformedModelException, IOException, TranslateException {

//        Pipeline pipeline = new Pipeline();
//        pipeline.add(new Resize(1152,648));
//        pipeline.add(new ToTensor());
//
//        String DETECTION_LABEL_0 = "oak";
//        List<String> synset = new ArrayList<>(1);
//        synset.add(DETECTION_LABEL_0);
//
//        Translator<Image, DetectedObjects> translator = YoloV8Translator.builder()
//                .setPipeline(pipeline)
//                .optSynset(synset)
//                .build();

        String modelPath = "C:\\Users\\Dreamcore\\OneDrive\\Desktop\\YoloV8\\runs\\detect\\train\\weights\\best.torchscript";
        String labels = "birch,oak";

        Criteria<Image, DetectedObjects> criteria =
                Criteria.builder()
                        .setTypes(Image.class, DetectedObjects.class)
                        .optModelPath(Paths.get(modelPath))
                        .optArgument("width", 640)
                        .optArgument("height", 640)
                        .optArgument("resize", true)
                        .optArgument("toTensor", true)
                        .optArgument("applyRatio", true)
                        .optArgument("threshold", 0.7f)
                        .optArgument("synset", labels)
                        // for performance optimization maxBox parameter can reduce number of
                        // considered boxes from 8400
                        .optArgument("maxBox", 1000)
                        .optTranslatorFactory(new YoloV8TranslatorFactory())
                        .build();

        ZooModel<Image, DetectedObjects> model = criteria.loadModel();
        Predictor<Image, DetectedObjects> predictor = model.newPredictor();

        Path imgPath = Paths.get("C:\\Users\\Dreamcore\\Downloads\\maxresdefault.jpg");
        BufferedImage img = ImageIO.read(imgPath.toFile());
        Image input = ImageFactory.getInstance().fromImage(img).resize(1152, 648, false);

        long start = System.nanoTime();
        DetectedObjects detectedObjects = predictor.predict(input);
        long end = System.nanoTime();
        long elasped = TimeUnit.NANOSECONDS.toMillis(end - start);
        System.out.println("Inference time " + elasped + "ms");

        System.out.println(detectedObjects);
        List<BoundingBox> boxes = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Double> prob = new ArrayList<>();
        for (Classifications.Classification obj : detectedObjects.items()) {
            DetectedObjects.DetectedObject objConvered = (DetectedObjects.DetectedObject) obj;
            BoundingBox box = objConvered.getBoundingBox();
            Rectangle rec = box.getBounds();
            boxes.add(rec);
            names.add(obj.getClassName());
            prob.add(obj.getProbability());
        }
        DetectedObjects converted = new DetectedObjects(names, prob, boxes);
        saveBoundingBoxImage(input, converted);
        System.out.println("Image saved at C:\\Users\\user\\Desktop\\detected.png");

        System.out.println(Engine.getInstance().getGpuCount());
    }

    private static void saveBoundingBoxImage(Image img, DetectedObjects detection) throws IOException {
        Path outputDir = Paths.get("C:\\Users\\Dreamcore\\OneDrive\\Desktop");
        Files.createDirectories(outputDir);
        img.drawBoundingBoxes(detection);
        Path imagePath = outputDir.resolve("detected.png");
        // OpenJDK can't save jpg with alpha channel
        img.save(Files.newOutputStream(imagePath), "png");
    }

}
