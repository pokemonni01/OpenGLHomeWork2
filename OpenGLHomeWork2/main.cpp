#include <stdlib.h>
#include <math.h>
#ifdef __APPLE__
#include <GLUT/glut.h>
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#else
#include <GL/glut.h>
#include <GL/glu.h>
#include <GL/gl.h>
#endif

const float FIRESPACE = 0.5;
const int WIDTH = 1120;
const int HEIGHT = 760;
const int TABLESIZE = 20;
const float STARTX = 4;
const float STARTY = 10;
const float CHARWIDTH = 2;
const float CHARHEIGHT = 3.5;
const float LINEHEIGHT = 1;

void myDisplay();
void initializeGL();
void setColor(float R,float G, float B);
void showTable();
void setRGBColor(int R, int G, int B);
void nextLine();
void initValue();


int charNumber;
float currentX;
float currentY;



int main(int argc,char** argv){
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_RGB);
    glutInitWindowSize(WIDTH,HEIGHT);
    glutInitWindowPosition(50,50);
    glutCreateWindow("OpenGL Student ID");
    glutDisplayFunc(myDisplay);
    initializeGL();
    glutMainLoop();
}//end main

void initValue(){
    charNumber = 0;
    currentX = STARTX;
    currentY = STARTY;
}

void initializeGL()
{
    glClearColor(0.0, 0.0, 0.0, 0.0);
    glColor3f(1.0, 1.0, 1.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0, WIDTH, 0, HEIGHT);
}

void myDisplay(){
    // Clear Window by black color
    glClear(GL_COLOR_BUFFER_BIT);
    initValue();

    glFlush();
}

void showTable(){
    //
    int i,j;
    glLineWidth(2.5);
    for(i=0;i<WIDTH;i+=TABLESIZE){
        setRGBColor(117, 117, 117);
        glBegin(GL_LINES);
        glVertex2f(i, 0);
        glVertex2f(i, HEIGHT);
        glEnd();
    }
    for(j=0;j<HEIGHT;j+=TABLESIZE){
        glBegin(GL_LINES);
        setRGBColor(117, 117, 117);
        glVertex2f(0, j);
        glVertex2f(WIDTH, j);
        glEnd();
    }
    for(i=0;i<WIDTH;i+=TABLESIZE*2){
        setRGBColor(224, 224, 224);
        glBegin(GL_LINES);
        glVertex2f(i, 0);
        glVertex2f(i, HEIGHT);
        glEnd();
    }
    for(j=0;j<HEIGHT;j+=TABLESIZE*2){
        glBegin(GL_LINES);
        setRGBColor(224, 224, 224);
        glVertex2f(0, j);
        glVertex2f(WIDTH, j);
        glEnd();
    }
}


void setRGBColor(int R, int G, int B){
    float r = (float) R/255;
    float g = (float) G/255;
    float b = (float) B/255;
    glColor3f(r, g, b);
}






