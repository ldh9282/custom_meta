/**
 * @class TimerElement
 * @desc timer-element 커스텀 태그
 */
class TimerElement extends HTMLElement {
    state = {};

    constructor() {
        super();

        const shadow = this.attachShadow({ mode: "open" });

        this.state = {
            time: TimerElement.getTimestamp(),
        };

        this.renderAll();
    }

    setState(theState) {
        this.state = { ...this.state, ...theState };
    }

    /**
     * @function renderAll
     * @desc 전체 렌더링
     */
    renderAll() {
        const shadow = this.shadowRoot;

        let html = "<div>";
        if (this.getAttribute("name")) {
            html += `	<span id="name">${this.getAttribute("name")}</span>`;
            html += " | ";
        }
        html += `	<span id="time">${this.state.time}</span>`;
        html += "</div>";
        shadow.innerHTML = html;

        setInterval(this.handleTimer.bind(this), 1000);
    }

    /**
     * @function handleTimer
     * @desc 타이머 핸들러
     */
    handleTimer() {
        this.setState({ time: TimerElement.getTimestamp() });
        this.renderTime();
    }

    /**
     * @function renderTime
     * @desc 타임 렌더링
     */
    renderTime() {
        const shadow = this.shadowRoot;
        $(this.shadowRoot).find("#time").text(this.state.time);
    }

    /**
     * @function getTimestamp
     * @desc 현재 타임스탬프 반환
     * @returns
     */
    static getTimestamp() {
        let now = new Date();

        let formatter = new Intl.DateTimeFormat("ko-KR", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit",
        });

        return formatter.format(now);
    }
}

customElements.define("timer-element", TimerElement);
