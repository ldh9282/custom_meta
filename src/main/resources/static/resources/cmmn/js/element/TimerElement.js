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
			time: TimerElement.getTimestamp()
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

        shadow.innerHTML = this.state.time;

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
        shadow.innerHTML = this.state.time;
    }

    /**
     * @function getTimestamp
     * @desc 현재 타임스탬프 반환
     * @returns
     */
    static getTimestamp() {
        return dateUtils.format(new Date(), 'yyyy-MM-dd HH:mm:ss');
    }
}

customElements.define("timer-element", TimerElement);
