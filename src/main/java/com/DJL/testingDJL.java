package com.DJL;

import ai.djl.MalformedModelException;
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

public class testingDJL {

    public static void main(String[] args) throws ModelNotFoundException, MalformedModelException, IOException, TranslateException {

        Pipeline pipeline = new Pipeline();
        pipeline.add(new Resize(1152,648));
        pipeline.add(new ToTensor());

        String DETECTION_LABEL_0 = "oak";
        List<String> synset = new ArrayList<>(1);
        synset.add(DETECTION_LABEL_0);

        Translator<Image, DetectedObjects> translator = YoloV8Translator.builder()
                .setPipeline(pipeline)
                .optSynset(synset)
                .build();

        Criteria<Image, DetectedObjects> criteria =
                Criteria.builder()
                        .setTypes(Image.class, DetectedObjects.class)
                        .optModelPath(Paths.get("C:\\Users\\user\\IdeaProjects\\launchermod\\src\\main\\java\\com\\example\\best.torchscript"))
                        .optArgument("width", 640)
                        .optArgument("height", 640)
                        .optArgument("resize", true)
                        .optArgument("toTensor", true)
                        .optArgument("applyRatio", true)
                        .optArgument("threshold", 0.7f)
                        .optArgument("synset", "birch,oak")
                        // for performance optimization maxBox parameter can reduce number of
                        // considered boxes from 8400
                        .optArgument("maxBox", 1000)
                        .optTranslatorFactory(new YoloV8TranslatorFactory())
                        .build();

        ZooModel<Image, DetectedObjects> model = criteria.loadModel();
        Predictor<Image, DetectedObjects> predictor = model.newPredictor();

        Path imgPath = Paths.get("C:\\Users\\user\\Downloads\\Screenshot 2024-06-12 151128.png");
        BufferedImage img = ImageIO.read(imgPath.toFile());
        Image input = ImageFactory.getInstance().fromImage(img);

        DetectedObjects detectedObjects = predictor.predict(input);
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
    }

    private static void saveBoundingBoxImage(Image img, DetectedObjects detection) throws IOException {
        Path outputDir = Paths.get("C:\\Users\\user\\Desktop");
        Files.createDirectories(outputDir);

        img.drawBoundingBoxes(detection);

        Path imagePath = outputDir.resolve("detected.png");
        // OpenJDK can't save jpg with alpha channel
        img.save(Files.newOutputStream(imagePath), "png");
    }

}
