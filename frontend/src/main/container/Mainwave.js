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

        /* 중간을 각각 넓이, 높이를 2로 나눈 값으로 지정 */
        this.centerX = stageWidth / 2;
        this.centerY = stageHeight / 2;
        this.pointGap = this.stageWidth / (this.totalPoints - 1);

        /* 초기화 함수로 넘어가기 */
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
    
        for (let i = 0; i < this.totalPoints; i++) {

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
        
            /* 공의 위치 변경하기 */
            if (i !== 0 && i !== this.numberOfPoints - 1) {
                this.points[i].update();
            }
        }
        
        /* 붓을 오른쪽 모서리부터 왼쪽 모서리 그리고 첫번째 점 위치까지 옮기면서 색칠해줍니다. */
        ctx.lineTo(prevX, prevY);
        ctx.lineTo(this.stageWidth, this.stageHeight);
        // ctx.lineTo(0, this.stageHeight);
        ctx.lineTo(this.points[0].x, this.points[0].y);
    
        /* 색상 RED & 채우기 */
        // ctx.fillStyle = '#ff0000';
        ctx.fill();
    
        /* 붓 끝내기 */
        ctx.closePath();
    }
}