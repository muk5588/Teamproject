/**
 * @name SE_QuickEditor_Image
 * @description Quick Editor Image function Class
 * @author NHN AjaxUI Lab - cielo
 * @version 1.0
 * @since 2009.10
 */
nhn.husky.SE_QuickEditor_Image = jindo.$Class({
    name : "SE_QuickEditor_Image",
    _aQEImg : [],
    elCurrentFocus : "",
    nMaxWidth : Infinity,
    nMaxHeight : Infinity,

    $init : function(elAppContainer){
    },

    $ON_MSG_APP_READY : function(){
        this.elEventTarget = null;
        this.elElement = null;
        this.welElement = null;
        this.nMaxWidth = (nhn.husky.SE2M_Configuration.QuickEditor.Image.nImageMaxWidthSize || Infinity);
        this.nMaxHeight = (nhn.husky.SE2M_Configuration.QuickEditor.Image.nImageMaxHeightSize || Infinity);
        this.bImgAutoAdjust = true;	/* true : 이미지의 가로/세로 비율을 유지함, false : 이미지의 가로/세로 비율을 유지하지 않고 각각 조절 가능함 */
    },

    _assignHTMLObjects : function(elAppContainer){
        this.oApp.exec("LOAD_HTML", ["qe_image"]);
        var self = this;

        this.elQELayer = jindo.$$.getSingle(".q_img_wrap", elAppContainer);
        this.elQELayer.style.zIndex = 150;
        this.elBtnBGPalette = jindo.$$.getSingle(".husky_se2m_img_qe_bgcolor_btn", this.elQELayer);
        this.elBtnResetPalette = jindo.$$.getSingle(".se2_sreset", this.elQELayer);
        this.elPanelBGPaletteHolder = jindo.$$.getSingle(".husky_se2m_img_qe_bg_paletteHolder", this.elQELayer);
        this.elPanelAlign0 = jindo.$$.getSingle(".se2_align0", this.elQELayer);
        this.elPanelAlign1 = jindo.$$.getSingle(".se2_align1", this.elQELayer);
        this.elPanelAlign2 = jindo.$$.getSingle(".se2_align2", this.elQELayer);
        this.elPanelImgWidth = jindo.$$.getSingle(".widthimg", this.elQELayer);
        this.elPanelImgHeight = jindo.$$.getSingle(".heightimg", this.elQELayer);
        this.elCheckImgAutoAdjust = jindo.$$.getSingle(".se2_srate", this.elQELayer);	/* 이미지의 가로/세로 길이를 따로따로 설정할 수 있도록 함 */
        this.elCheckImgAutoAdjust.checked = true;										/* 기본값은 checked (-> 가로 세로 비율 유지함, 가로 길이가 줄 때 세로 길이도 비율에 맞추어 줄어듬) */
        this.elPanelImgBorder = jindo.$$.getSingle(".bordersize", this.elQELayer);
        this.elPanelHighEdit = jindo.$$.getSingle(".se2_highedit", this.elQELayer);

        new jindo.NumericStepper(jindo.$$.getSingle(".se2_numberStepper", this.elQELayer), {
            sClassPrefix : '',
            nStep : 1, 					// (Number) 가감(+/-)이  일어나는 단위를 의미합니다.
            nMax : 10,	 				// (Number) 최대값의 제한값이 설정됩니다.
            nMin : 0, 					// (Number) 최소값의 제한 값이 설정됩니다.
            nDefaultValue : 0,			// (Number) Input Box안에 들어갈 default value
            bUseMouseWheel : false,		// 마우스 휠을 사용하여, input vale를 컨트할 수 있게 할지 여부 세팅
            bInputReadOnly : true		// Input Box의 속성을 결정하는 option
        }).attach({
            beforeChange : function(oCustomEvent){
                if(oCustomEvent.nValue > oCustomEvent.nMax || oCustomEvent.nValue < oCustomEvent.nMin){
                    oCustomEvent.stop();
                }
            },
            change : function(oCustomEvent){
                self.oApp.exec("IMG_QE_SET_BORDER", [oCustomEvent.nValue]);
            }
        });
    },

    $BEFORE_EXECCOMMAND : function(){
        this.oApp.exec("CLOSE_QE_LAYER");
    },

    $BEFORE_CHANGE_EDITING_MODE : function(sMode, bNoFocus){
        if(sMode !== "WYSIWYG"){
            this.welElement = null;
            this.oApp.exec("CLOSE_QE_LAYER");
        }
    },

    _hasThumbnail : function(id) {
        return this.oApp.hasThumbnail && this.oApp.hasThumbnail(id);
    },

    /**
     * 퀵에디터 판넬의 각 버튼에 이벤트 설정
     */
    $LOCAL_BEFORE_FIRST : function(sMsg){
        // CHANGE_EDITING_MODE 시에 불필요한 이벤트 등록을 피하기 위해 추가된 코드임
        if(!!sMsg.match(/(REGISTER_CONVERTERS|CHANGE_EDITING_MODE)/)){
            this.oApp.acceptLocalBeforeFirstAgain(this, true);
            return true;
        }
        this._assignHTMLObjects(this.oApp.htOptions.elAppContainer);
        this.oApp.registerBrowserEvent(this.elBtnBGPalette, "click", "IMG_QE_TOGGLE_BGC_PALETTE");
        this.oApp.registerBrowserEvent(this.elBtnResetPalette, "click", "IMG_QE_RESET_PALETTE");

        this.oApp.registerBrowserEvent(this.elPanelAlign0, "click", "IMG_QE_ALIGN_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelAlign1, "click", "IMG_QE_ALIGN_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelAlign2, "click", "IMG_QE_ALIGN_PALETTE");

        this.oApp.registerBrowserEvent(this.elPanelImgWidth, "blur", "IMG_QE_RESIZE_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelImgHeight, "blur", "IMG_QE_RESIZE_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelImgWidth, "focus", "IMG_QE_RESIZE_START_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelImgHeight, "focus", "IMG_QE_RESIZE_START_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelImgWidth, "mousedown", "IMG_QE_RESIZE_START_PALETTE");
        this.oApp.registerBrowserEvent(this.elPanelImgHeight, "mousedown", "IMG_QE_RESIZE_START_PALETTE");
        this.oApp.registerBrowserEvent(this.elCheckImgAutoAdjust, "click", "IMG_SIZE_ADJUST");
        this.oApp.registerBrowserEvent(this.elPanelHighEdit, "click", "HIGH_EDIT_CLICK");
    },

    $ON_EVENT_EDITING_AREA_DBLCLICK : function(ev){
        var elDBClickEventTarget = ev.element;
        if(elDBClickEventTarget.tagName != "IMG"){
            return;
        }

        if(this._hasThumbnail(elDBClickEventTarget.id)){
            this.oApp.exec("CLOSE_QE_LAYER"); // 퀵에디터 닫음
        }
    },

    $ON_HIGH_EDIT_CLICK : function(){
        if(!!this.welElement && this._hasThumbnail(this.elElement.id) && this.welElement.attr("s_subtype") == "photo"){
            this.oApp.exec("IMG_SE_REEDIT_PHOTO", [this.welElement]);
        }
    },

    $ON_EVENT_EDITING_AREA_MOUSEDOWN : function(ev){
        this.elEventTarget = null;

        if(ev.element.tagName != null && (ev.element.tagName).toLowerCase() == 'img'){
            this.elEventTarget = ev.element;
        }
    },

    $ON_EVENT_EDITING_AREA_MOUSEUP : function(ev){
        if(!!this.elEventTarget && this.elEventTarget == ev.element){
            this._mouseUp_WYSIWYGDoc(ev);
        }else{
            this.elEventTarget = null;
        }
    },

    $ON_REGISTER_CONVERTERS : function(){
        this.oApp.exec("ADD_CONVERTER", ["IR_TO_DB", jindo.$Fn(this.irToDB, this).bind()]);
    },

    irToDB : function(sHtml){
        // irToDB시점에 임의로 추가한 속성을 제거한다.
        sHtml = sHtml.replace(/sQEId\s*=\s*\"([^>"]*)\"/i,'');
        // 'element의 초기속성'을 담은 array를 초기화
        this._aQEImg = [];
        return sHtml;
    },

    _mouseUp_WYSIWYGDoc : function(wevE){
        if(!wevE.element || 'IMG' != wevE.element.tagName){
            return;
        }

        // 특정 이미지만 퀵 에디터를 사용할 수 없음!
        this.elElement = wevE.element;
        this.welElement = jindo.$Element(wevE.element);

        if('false' == this.welElement.attr("imgqe")){
            return;
        }

        // 기본 bordercolor를 셋팅
        this.elElement.style.borderColor = this.elElement.style.borderLeftColor || "rgb(0, 0, 0)";

        // sQEId 셋팅
        var sQEId = this.welElement.attr("sQEId");
        if('undefined' == typeof(sQEId) || !sQEId){
            sQEId = nhn.husky.SE2M_Utils.getUniqueId("QE_");
            this._aQEImg[sQEId] = this._getInfoImage(this.elElement);
            this.welElement.attr("sQEId", sQEId); // key생성
        } else if("undefined" == typeof ( this._aQEImg[sQEId] ) || !this._aQEImg[sQEId] ) {
            //[SMARTEDITORSUS-356] 임시저장 이후 비워진 array채우는 역할.
            // 에디터 초기화시 call되는 func이 생기면 그쪽으로 리팩토링이 필요함.
            this._aQEImg[sQEId] = this._getInfoImage(this.elElement);
        }

        this._panelReSetting();
        this._hideImagePalette();

        this.oApp.exec("OPEN_QE_LAYER", [this.elElement, this.elQELayer, "img"]);
    },

    /**
     * 이미지 정보를 저장
     * @param {Object} elElement
     */
    _getInfoImage : function(elElement){
        var htImageInfo = {
            width : elElement.width,
            height : elElement.height,
            rwidth : this.welElement.attr("rwidth") || '', // 삽입 시의 이미지 width
            rheight : this.welElement.attr("rheight") || '', // 삽입 시의 이미지 height
            border : elElement.border || 0,
            borderColor : elElement.style.borderLeftColor || "rgb(0, 0, 0)" ,
            align : elElement.align || ""
        };
        return htImageInfo;
    },

    _panelReSetting : function(){
        var elImage = this.elElement;
        this.elPanelImgBorder.value = (this.welElement.attr("border") || 0);
        this.elPanelImgWidth.value = (elImage.width || this.welElement.width());
        this.elPanelImgHeight.value = (elImage.height || this.welElement.height());
        this.elBtnBGPalette.style.backgroundColor = elImage.style.borderLeftColor || "rgb(0, 0, 0)";

        // 고급편집 버튼 보여주기 여부 체크 (이미지 업로드로 올린 사진 && 첨부영역에 썸네일이 있어야 함)
        if(this._hasThumbnail(elImage.id) && this.welElement.attr("s_subtype") == "photo"){
            jindo.$Element(this.elPanelHighEdit).show();
        }else{
            jindo.$Element(this.elPanelHighEdit).hide();
        }
    },

    _hideImagePalette : function(){
        this.elPanelBGPaletteHolder.parentNode.style.display = "none";
        this.oApp.exec("HIDE_COLOR_PALETTE");
    },

    /**
     * 초기화 버튼 클릭시 사용
     * @param {Event} weEvent
     */
    $ON_IMG_QE_RESET_PALETTE : function(weEvent){
        var sQEId = this.welElement.attr("sQEId");
        var elTemp = this._aQEImg[sQEId];
        if(!!elTemp){
            var htImageSize = {
                "width" : elTemp.width,
                "height" : elTemp.height,
                "rwidth" : elTemp.rwidth,
                "rheight" : elTemp.rheight
            };
            this.welElement.css("borderWidth", elTemp.border+"px");
            this.welElement.css("borderStyle", "solid");
            this.welElement.css("borderColor", elTemp.borderColor);
            this.welElement.attr("border", elTemp.border);
            this.welElement.attr("align", elTemp.align);
            this.welElement.attr("rwidth", elTemp.rwidth);
            this.welElement.attr("rheight", elTemp.rheight);
            this._changeSizeValue(htImageSize);
        }

        this._panelReSetting();
    },

    /**
     * 정비례하게 증감
     * @param {Event} weEvent
     */
    $ON_IMG_QE_RESIZE_START_PALETTE : function(weEvent){
        var elCurrentFocus = jindo.$Element(weEvent.element);
        if(elCurrentFocus.isEqual(this.elPanelImgWidth)){
            this.elPanelImgWidth.readOnly = false;
            this.elPanelImgHeight.readOnly = true;
        }else{
            this.elPanelImgWidth.readOnly = true;
            this.elPanelImgHeight.readOnly = false;
        }
    },

    $ON_IMG_QE_ENROLL_ATTR : function(welQEImg){
        welQEImg.attr("imgqe",true);
    },

    $ON_IMG_QE_RESIZE_PALETTE : function(weEvent){
        this._resizeImage(weEvent.element);
        weEvent.stop();
    },

    _resizeImage : function(elEvent){
        var elImage = this.elElement;
        var elCurrentFocus = jindo.$Element(elEvent);
        var nImgAfterWidth = this.elPanelImgWidth.value;
        var nImgAfterHeight = this.elPanelImgHeight.value;
        var nImgBeforeWidth = (elImage.width || this.welElement.width());
        var nImgBeforeHeight = (elImage.height || this.welElement.height());
        var nReserveWidth = this.welElement.attr("rwidth");
        var nReserveHeight = this.welElement.attr("rheight");
        var nTemp;

        if(elCurrentFocus.isEqual(this.elPanelImgWidth)){ // change width
            if(this.nMaxWidth > nImgAfterWidth && nImgAfterWidth > 0){
                nTemp = nImgAfterWidth/nImgBeforeWidth;
                var htImageSize = {
                    nAdjustWidth : Number(nImgAfterWidth),
                    nAdjustHeight : Number(nImgBeforeHeight * nTemp),
                    nImgBeforeWidth : nImgBeforeWidth,
                    nImgBeforeHeight : nImgBeforeHeight,
                    nReserveWidth : nReserveWidth,
                    nReserveHeight : nReserveHeight
                };

                if(this.bImgAutoAdjust){					/* 가로/세로 비율 유지하면서 사이즈 조절 */
                    var htAdjustImageSize = this._adjustViewImageSize(htImageSize);
                    this._changeSizeValue(htAdjustImageSize);
                }else{
                    var htAdjustSize = this._adjustViewImageWidth(htImageSize);
                    this._changeWidth(htAdjustSize);
                }
            }
        }else{ // change height
            if(this.nMaxHeight > nImgAfterHeight && nImgAfterHeight > 0){
                nTemp = nImgAfterHeight/nImgBeforeHeight;
                var htImageSize = {
                    nAdjustWidth : Number(nImgBeforeWidth * nTemp),
                    nAdjustHeight : Number(nImgAfterHeight),
                    nImgBeforeWidth : nImgBeforeWidth,
                    nImgBeforeHeight : nImgBeforeHeight,
                    nReserveWidth : nReserveWidth,
                    nReserveHeight : nReserveHeight
                };

                if(this.bImgAutoAdjust){					/* 가로/세로 비율 유지하면서 사이즈 조절 */
                    var htAdjustImageSize = this._adjustViewImageSize(htImageSize);
                    this._changeSizeValue(htAdjustImageSize);
                }else{
                    this.welElement.attr("noadjust", true);
                    this._changeHeight(htImageSize);
                }
            }
        }

        // 속성 width와 height보다 style의 width와 height 우선시 되기 때문에 둘다 변경해 준다.
        this.oApp.exec("OPEN_QE_LAYER", [elImage, this.elQELayer, "img"]);
        this._panelReSetting();
    },

    _changeSizeValue : function(htSize){
        var elImage = this.elElement;
        elImage.width = htSize.width;
        elImage.style.width = htSize.width + "px";
        elImage.height = htSize.height;
        elImage.style.height = htSize.height + "px";

        // 이미지 퀵 에디터에서 설정한 이미지의 width/height 를 이미지에 적용 ([SMARTEDITORSUS-279] related)
        this.welElement.attr("rwidth", htSize.rwidth);
        this.welElement.css("rwidth", htSize.rwidth + "px");
        this.welElement.attr("rheight", htSize.rheight);
        this.welElement.css("rheight", htSize.rheight+ "px");
    },

    _changeWidth : function(htSize){
        var elImage = this.elElement;
        elImage.width = htSize.width;
        elImage.style.width = htSize.width + "px";
        elImage.height = htSize.height;
        elImage.style.height = htSize.height + "px";

        // 이미지 퀵 에디터에서 설정한 이미지의 width 를 이미지에 적용, height 유지 ([SMARTEDITORSUS-279] related)
        this.welElement.attr("rwidth", htSize.rwidth);
        this.welElement.css("rwidth", htSize.rwidth + "px");
        this.welElement.attr("height", htSize.height);
        this.welElement.css("height", htSize.height + "px");
    },

    _changeHeight : function(htSize){
        var elImage = this.elElement;
        elImage.height = htSize.nAdjustHeight;
        elImage.style.height = htSize.nAdjustHeight + "px";

        // 이미지 퀵 에디터에서 설정한 이미지의 height 를 이미지에 적용 ([SMARTEDITORSUS-279] related)
        this.welElement.attr("rheight", htSize.nAdjustHeight);
        this.welElement.css("rheight", htSize.nAdjustHeight + "px");
        this.welElement.attr("rwidth", htSize.nReserveWidth);
        this.welElement.css("rwidth", htSize.nReserveWidth + "px");
    },

    /*
     * [SMARTEDITORSUS-258, 276, 279]
     *		1) 에디터 가로폭 개선에 따라 이미지 첨부 시 가로폭 크기에 맞는 이미지의 사이즈 조절이 필요함
     *		2) 에디터 가로폭 꺽쇠가 노출되는 경우에만 에디터 본문 안에 보이는 이미지의 사이즈를 조절함
     *		3) 사용자가 조절하는 경우(포토업로더, 이미지 퀵 에디터에서 변경) 이외에는 이미지의 사이즈를 조절하지 않음
     * [SMARTEDITORSUS-1858] 가로폭 꺽쇠 적용과 상관없이 큰 이미지는 에디터 가로폭에 맞게 리사이즈되도록 변경
     */
    _adjustViewImageSize : function(htSize){
        var htImageSize = {
            "width" : htSize.nAdjustWidth,
            "height" : htSize.nAdjustHeight,
            "rwidth" : htSize.nAdjustWidth,
            "rheight" : htSize.nAdjustHeight
        };

        var welWysiwygBody = jindo.$Element(this.oApp.getWYSIWYGDocument().body);
        if(welWysiwygBody){
            var nEditorWidth = welWysiwygBody.width();
            if(htSize.nAdjustWidth > nEditorWidth){
                htImageSize.width = htSize.nImgBeforeWidth;
                htImageSize.height = htSize.nImgBeforeHeight;
                htImageSize.rwidth = htSize.nReserveWidth;
                htImageSize.rheight = htSize.nReserveHeight;
                var msg = this.oApp.$MSG("SE_QuickEditor_Image.exceedMaxSize").replace("${nEditorWidth}", nEditorWidth);
                alert(msg);
            }
        }
        return htImageSize;
    },

    _adjustViewImageWidth : function(htSize){
        var htImageSize = {
            "width" : htSize.nAdjustWidth,
            "height" : htSize.nImgBeforeHeight,
            "rwidth" : htSize.nAdjustWidth,
            "rheight" : htSize.nReserveHeight
        };

        var welWysiwygBody = jindo.$Element(this.oApp.getWYSIWYGDocument().body);
        if(welWysiwygBody){
            var nEditorWidth = welWysiwygBody.width();
            if(htSize.nAdjustWidth > nEditorWidth){
                htImageSize.width = htSize.nImgBeforeWidth;
                htImageSize.rwidth = htSize.nReserverWidth;
                var msg = this.oApp.$MSG("SE_QuickEditor_Image.exceedMaxSize").replace("${nEditorWidth}", nEditorWidth);
                alert(msg);
            }
        }
        return htImageSize;
    },

    /**
     * '가로 세로 비율 유지' 옵션 추가 ([SMARTEDITORSUS-150] related)
     * 1) 옵션 체크 : 첨부한 사진의 가로나 세로의 길이를 변경할 경우 고정 비율로 이미지 사이즈가 조절됨 (this.bImgAutoAdjust -> true)
     * 2) 옵션 체크 해제 : 첨부한 사진의 가로/세로 길이를 따로따로 설정할 수 있음 (this.bImgAutoAdjust -> false)
     */
    $ON_IMG_SIZE_ADJUST : function(){
        this.bImgAutoAdjust = (this.elCheckImgAutoAdjust.checked) ? true : false;
    },

    $ON_CLOSE_SUB_LAYER_QE : function(){
        if(typeof this.elPanelBGPaletteHolder != 'undefined'){
            this.elPanelBGPaletteHolder.parentNode.style.display = "none";
        }
        if(typeof this.elPanelBGIMGPaletteHolder != 'undefined'){
            this.elPanelBGIMGPaletteHolder.parentNode.style.display = "none";
        }
    },

    $ON_IMG_QE_TOGGLE_BGC_PALETTE : function(){ // border BGC 버튼 토글
        if(this.elPanelBGPaletteHolder.parentNode.style.display == "block"){
            this._hideImagePalette();
        }else{
            this._showImagePalette();
        }
    },

    $ON_IMG_QE_ALIGN_PALETTE : function(wevE){
        var welButtonEvent = jindo.$Element(wevE.element); // Click이 일어난 정렬 버튼
        if(welButtonEvent.hasClass("left")){
            this.welElement.attr("align", "left");
            this.welElement.css("clear", "left");
        }else if(welButtonEvent.hasClass("right")){
            this.welElement.attr("align", "right");
            this.welElement.css("clear", "right");
        }else{
            this.welElement.attr("align", "");
            this.welElement.css("clear", "both");
        }
    },

    $ON_IMG_QE_SET_BORDER : function(nBorderSize){  // border size
        this.welElement.css("borderWidth", nBorderSize + "px");
        this.welElement.css("borderStyle", "solid");
        this.welElement.attr("border", nBorderSize);
        this.elElement.border = nBorderSize;
    },

    $ON_IMG_QE_SET_BGC_FROM_PALETTE : function(sColorCode){ // border color
        this.elBtnBGPalette.style.backgroundColor = sColorCode;
        this.elElement.style.borderColor = sColorCode;
        if(this.elPanelBGPaletteHolder.parentNode.style.display == "block"){
            this._hideImagePalette();
        }
    },

    _showImagePalette : function(){
        this.elPanelBGPaletteHolder.parentNode.style.display = "block";
        this.oApp.exec("SHOW_COLOR_PALETTE", ["IMG_QE_SET_BGC_FROM_PALETTE", this.elPanelBGPaletteHolder]);
    }
});