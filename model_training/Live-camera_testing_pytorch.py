# -*- coding: utf-8 -*-
"""
Created on Mon Dec 23 19:14:38 2024

@author: Dell
"""

import os
from ultralytics import YOLO
import cv2
import yaml
from pathlib import Path

# Load the trained YOLO model
model_path = r"C:\Users\ASUS\Documents\yolo_4\Models\Non_pest.pt"  # Path to your saved model
model = YOLO(model_path)  # Load the model

# Live camera capture
cap = cv2.VideoCapture(0)  # 0 for default camera

while cap.isOpened():
    ret, frame = cap.read()
    if not ret:
        break

    # Perform inference on the live frame
    results = model(frame)  # Perform inference on the frame

    # Render the results using the 'plot' method
    if results:
        annotated_frame = results[0].plot()  # Annotate the frame with detection results
        cv2.imshow("Live Inference", annotated_frame)  # Display the annotated frame
    else:
        cv2.imshow("Live Inference", frame)  # Show original frame if no detections

    # Press 'q' to exit
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
