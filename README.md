# Image analysis
### Lynda.com: Code Clinic for Java - Image analysis

## Code clinic is a monthly course where a unique problem is introduced to a collection of lynda.com authors. In response, each author will create a solution using their programming language of choice. You can learn several things from code clinic, different approaches to solving a problem, the pros and cons of different languages, and some tips and tricks to incorporate into your own coding practices. 

## Images are nothing more than specialized and well defined sets of data. An image consists of pixels. Pixels consists of data representing the color of the pixel and in some cases the pixel's transparency. The pixels are arranged in rows and columns. When assembled correctly they represent an image. Our brains are very good at recognizing patterns, but computers are not. Think about captcha security devices, those puzzles you sometimes see when logging into a website.

## The captcha asks what letters and numbers are in the image. Information is obscured by random lines, sometimes overlapping transparent blocks of color. All of those intersecting shapes makes it difficult for a computer program to separate the background noise from the actual data. Another example is the tests to determine color blindness. Letters and numbers are hidden in a circle filled with different colored dots. If you are color blind you will not be able to see the numbers. For a computer program this can be incredibly difficult as it requires detecting an edge as well as recognizing the overall shape.

## It's difficult even for the most advanced programming. In this problem we're trying to solve a common problem for many photographers, plagiarism. A photographer will take a photo and post it on the internet only to discover that someone has stolen their image and placed a subset of that image on their website. For example, here's an image and then a cropped version of that image. It would be extremely handy if there was a program searching the internet for cropped versions of an original image so that a photographer could protect their rights.

## In fact Google image search will do just that, but we're curious how it works and what the required code might look like. Here's the challenge. Given two images, determine if one image is a subset of the other image. We'll assume that they are both JPEG files, that the resolution is the same, as well as the bit depth. We've provided a set of images. The program should return a table showing which images are cropped versions of other images.

### Source: https://www.lynda.com/Java-tutorials/Image-analysis/162454/186688-4.html
