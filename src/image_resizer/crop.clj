(ns image-resizer.crop
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [java.awt.image BufferedImage]
   [org.imgscalr Scalr]))

(defn- buffered-image [image]
  (if (instance? BufferedImage image)
    image
    (ImageIO/read image)))

(defn- dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn crop-fn [width height]
  (fn [image]
    (Scalr/crop (buffered-image image) 0 0 width height nil)))

(defn crop-width-fn [width]
  (fn [image]
    (let [buffered (buffered-image image)
          [_ height] (dimensions buffered)]
      (Scalr/crop buffered 0 0 width height nil))))

(defn crop-height-fn [height]
  (fn [image]
    (let [buffered (buffered-image image)
          [width _] (dimensions buffered)]
      (Scalr/crop buffered 0 0 width height nil))))