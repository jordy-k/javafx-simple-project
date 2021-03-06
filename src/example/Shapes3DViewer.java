/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.AmbientLight;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
/**
 *
 * @author 62499
 */
public class Shapes3DViewer extends Application {

  PhongMaterial material;

  @Override
  public void start(Stage stage) {
    material = new PhongMaterial();
    material.setDiffuseColor(Color.FIREBRICK);
    material.setSpecularColor(Color.YELLOW);

    PointLight pointLight = new PointLight(Color.WHITE);
    pointLight.setTranslateX(100);
    pointLight.setTranslateY(100);
    pointLight.setTranslateZ(-300);
    pointLight.setRotate(90);

    AmbientLight ambient = new AmbientLight();

    Group g1 = createSphereGroup(200, "Default light");
    Group g2 = createSphereGroup(200, "Point light");
    Group g3 = createSphereGroup(200, "Ambient light");

    g2.getChildren().add(pointLight);
    g3.getChildren().add(ambient);

    SubScene s1 = createSubScene(g1, 500, 500);
    SubScene s2 = createSubScene(g2, 500, 500);
    SubScene s3 = createSubScene(g3, 500, 500);

    HBox root = new HBox();
    root.getChildren().addAll(s1, s2, s3);

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Group createSphereGroup(double radius, String text) {
    Box c = new Box(radius,radius,radius);
    c.setMaterial(material);
    c.setDrawMode(DrawMode.FILL);
    c.setTranslateX(radius * 1.33);
    c.setTranslateY(radius * 1.33);
    c.setRotate(70);
    c.setRotationAxis(new Point3D(300, 15, 20));
    Label lbl = new Label(text);
    lbl.setStyle("-fx-text-fill: red;-fx-font-size: 18pt;");
    return new Group(c, lbl);
  }

  private SubScene createSubScene(Group group, double width, double height) {
    SubScene s = new SubScene(group, width, height);
    s.setCamera(new PerspectiveCamera());
    s.setFill(Color.color(.1, .1, .1));
    return s;
  }
}