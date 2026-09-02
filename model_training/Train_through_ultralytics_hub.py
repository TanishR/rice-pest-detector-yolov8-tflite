from ultralytics import YOLO, checks, hub

def main():
    checks()
    hub.login('2f63ebdfa8790e87071b4ca956d4452d1c034a7c4d')
    model = YOLO('https://hub.ultralytics.com/models/Uc4iXXuTGK8AJgolSlYJ')
    results = model.train()

if __name__ == "__main__":
    main()
