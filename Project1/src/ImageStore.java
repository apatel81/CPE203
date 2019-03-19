import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;

final class ImageStore
{
   private Map<String, List<PImage>> images;
   private List<PImage> defaultImages;

   public ImageStore(PImage defaultImage)
   {
      this.images = new HashMap<>();
      defaultImages = new LinkedList<>();
      defaultImages.add(defaultImage);
   }

   public Map<String, List<PImage>> images() {
      return this.images;
   }

   public List<PImage> getImageList(String key)
   {
      return this.images.getOrDefault(key, this.defaultImages);
   }


}
