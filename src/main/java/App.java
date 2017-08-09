import models.Rectangle;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;


/**
 * Created by Guest on 8/9/17.
 */
public class App {
          public static void main(String[] args) {

            get("/", (req, res) -> {
                Map<String, ArrayList<Rectangle>> model = new HashMap<>();
                ArrayList myRectangleArrayList = Rectangle.getAll();
                model.put("myRectangles", myRectangleArrayList);
                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());

            post("/rectangles/new", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                int height = Integer.parseInt(req.queryParams("height"));
                int width = Integer.parseInt(req.queryParams("width"));
                models.Rectangle myRectangle = new models.Rectangle(height, width);
                model.put("myRectangle", myRectangle);

                if (myRectangle.getShape()) {
                    models.Cube myCube = new models.Cube(myRectangle);
                    model.put("myCube", myCube);
                }
                return new ModelAndView(model, "rectangle-detail.hbs");
            }, new HandlebarsTemplateEngine());
        }
    }

