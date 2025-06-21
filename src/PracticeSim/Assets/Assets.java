package PracticeSim.Assets;

import java.awt.image.BufferedImage;;

public class Assets {

public static BufferedImage home, park,death,list,dog,cat,bird,action,squirrel;
public static BufferedImage Pitbull,RatTerrier,Poodle,Husky,Maltese,Siamese,Tabicat,Persian,Sphinx,Pigeon,Hawk,Parrot,Parakeet;
public static String temp = "/textures/home.png";
	public static void init() {

		home = ImageLoader.loadImage(temp);
		park = ImageLoader.loadImage("/textures/park.jpg");
		death = ImageLoader.loadImage("/textures/unknown.jpg");
		list = ImageLoader.loadImage("/textures/AnimalListSection.png");
		dog =ImageLoader.loadImage("/textures/dog.png");
		cat = ImageLoader.loadImage("/textures/cat2.png");
		bird = ImageLoader.loadImage("/textures/bird.jpg");
		action = ImageLoader.loadImage("/textures/ActionSection.png");
		squirrel = ImageLoader.loadImage("/textures/squirrel.jpg");
		Pitbull = ImageLoader.loadImage("/textures/pitbull.png");
		RatTerrier = ImageLoader.loadImage("/textures/ratterrier.png");
		Poodle = ImageLoader.loadImage("/textures/poodle.png");
		Husky = ImageLoader.loadImage("/textures/husky.png");
		Maltese = ImageLoader.loadImage("/textures/maltese.png");
		Siamese = ImageLoader.loadImage("/textures/siamese.png");
		Tabicat = ImageLoader.loadImage("/textures/tabicat.png");
		Persian = ImageLoader.loadImage("/textures/persain.png");
		Sphinx = ImageLoader.loadImage("/textures/sphinx.png");
		Pigeon = ImageLoader.loadImage("/textures/pigeon.png");
		Hawk = ImageLoader.loadImage("/textures/hawk.png");
		Parrot = ImageLoader.loadImage("/textures/parrot.png");
		Parakeet = ImageLoader.loadImage("/textures/parakeet.png");

	}

}
