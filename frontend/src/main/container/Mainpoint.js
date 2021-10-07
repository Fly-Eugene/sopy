export class Point {

    constructor(index, x, y) {
        this.x = x;
        this.y = y;
        this.fieldY = y; // 기본 Y 중심
        this.speed = 0.01;
        this.cur = index; // 각 점이 최대한 평행하지 않도록 각각 다른 시작점을 가지게 합니다.
        this.max = Math.random() * 100 + 150;
    }
  
    update() {
      this.cur += this.speed;
      this.y = this.fieldY + (Math.sin(this.cur) * this.max);
    }
  }