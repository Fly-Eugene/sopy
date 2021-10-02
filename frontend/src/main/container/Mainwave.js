import {Point} from './Mainpoint.js';

export class Wave {
    constructor(index, totalPoints, color) {
        this.index = index;
        this.totalPoints = totalPoints;
        this.color = color;
        this.points = [];
        // this.numberOfPoints = 6;
    }

    resize(stageWidth, stageHeight) {
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;

        // 화면의 중앙에 그릴 것이기 때문에, /2 를 한다.
        this.centerX = stageWidth / 2;
        this.centerY = stageHeight / 3;
        this.pointGap = this.stageWidth / (this.totalPoints - 1);

        // 포인트 함수를 실행해주기
        this.init();
    }

    init() {
        this.points = [];

        for (let i = 0; i < this.totalPoints; i++) {
            const point = new Point(this.index + i, this.pointGap * i, this.centerY)
            this.points[i] = point;
        }
    }

    draw(ctx) {
        ctx.beginPath();
        ctx.fillStyle = this.color;
    
        /* 곡선을 위해 이전의 좌표 기억하기 */
        let prevX = this.points[0].x;
        let prevY = this.points[0].y;
    
        /* 점의 시작점으로 붓 이동하기 */
        ctx.moveTo(prevX, prevY);
    
        for (let i = 1; i < this.totalPoints; i++) {

            if (i < this.totalPoints - 1){
                this.points[i].update();
            }
            const cx = (prevX + this.points[i].x) / 2;
            const cy = (prevY + this.points[i].y) / 2;
            
            // ctx.lineTo(cx, cy)
            ctx.quadraticCurveTo(prevX, prevY, cx, cy);
        
            /* 곡선을 그리기 위해 이전 좌표 업데이트하기 */
            prevX = this.points[i].x;
            prevY = this.points[i].y;
        
        }
        
        /* 붓을 오른쪽 모서리부터 왼쪽 모서리 그리고 첫번째 점 위치까지 옮기면서 색칠해줍니다. */
        ctx.lineTo(prevX, prevY);
        ctx.lineTo(this.stageWidth, this.stageHeight);
        // ctx.lineTo(0, this.stageHeight);
        ctx.lineTo(this.points[0].x, this.stageHeight);
    
        /* 색상 RED & 채우기 */
        // ctx.fillStyle = '#ff0000';
        ctx.fill();
    
        /* 붓 끝내기 */
        ctx.closePath();
    }
}