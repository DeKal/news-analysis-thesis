var CONSTANT = {
    getRootAreaURL : "getRootAreas/",
    getAreasURL : function(parentId) {
        return "getAreas/id/" + parentId;
    },
    getCameraURL : function(parentId) {
        return "getCamera/id/" + parentId;
    },
    getPositionURL : function(parentId) {
        return "getPosition/id/" + parentId;
    },
    searchByUser : function(data) {
        return "/oa-web/search/user/" + data;
    },
    searchByCamera : function(data) {
        return "/oa-web/search/camera/" + data;
    },
    searchByArea : function(data) {
        return "/oa-web/search/area/" + data;
    },
    searchByAll : function(data){
        return "/oa-web/search/all/" + data;
    },
    AREA_CODE : "AREA",
    CAMERA_CODE : "CAMERA",
    LIST_AREA_CODE : "LIST_AREA",
    LIST_CAMERA_CODE : "LIST_CAMERA",
    ROLE_CODE : "ROLE",
    CURRENT_USER_CODE : "USER",
    TREE_SELECT_NODE : "TREE_SELECT_NODE_WHEN_CLICKING_THUMBNAIL",
    MAIN_CONTENT_SHOW_AREAS : "SHOW_AREA_IN_MAIN_CONTENT_PANEL",
    MAIN_CONTENT_SHOW_LIST_PAGE : "SHOW_LIST_PAGE_IN_MAIN_CONTENT",
    MAIN_CONTENT_SHOW_VIDEO : " SHOW_VIDEO_STREAM",
    MAIN_CONTENT_REFRESH : "REFRESH",
    GET_CAMERA : "GET_CAMERA",
    GET_ROOT_AREA : "GET_ROOT",
    GET_AREA : "GET_AREA",
    MAX_ITEMS_PER_PAGE : 8,
    POPUP_ADD : "POPUP_ADD",
    POPUP_EDIT : "POPUP_EDIT",
    POPUP_DELETE : "POPUP_DELETE",
    UPDATE_AREA : "UPDATE_AREA",
    UPDATE_CAM : "UPDATE_CAM",
    DELETE_AREA : "DEL_AREA",
    DELETE_CAM : "DEL_CAM",
    ADD_NEW_AREA : "ADD_NEW_AREA",
    ADD_NEW_CAM : "ADD_NEW_CAM",
    RENDER_BREADCRUMBS : "RENDER_BREADCRUMBS",
    ADD_POPUP_TITLE : "Add new",
    EDIT_POPUP_TITLE : "Edit",
    DELETE_POPUP_TITLE : "Do you want to delete",
    SHOW_POS_INFO : "POP_UP_SHOW_POS_INFO",
    SHOW_POS_INFO_TITLE : "Position Info",
    OPEN_EDIT_PERSON_POPUP : "POP_UP_EDIT_PERSON",
    EDIT_PERSON_INFO_TITLE : "Edit person",
    DELETE_POSITION : "DELETE_POSITION",
    POSITION_CODE : "POSITION",
    REFRESH_VIDEO_STREAM : "REFRESH MAIN PAGE VIDEO STREAM",
    BACKEND_IMAGE_PATH : "/oa-web/backend/images/",
    GRID_SQUARE_SIZE : 20
}

var TEMPLATE = {
    HOME_THUMBNAIL_TEMPLATE : '<div class="col-lg-3 col-md-4 col-xs-6"> <div class="control-buttons %role%">'
            + '<button class="transparent-button edit-button-%button-id%">'
            + '<img class="control-button" src="assets/image/edit.png" alt="GCS" />'
            + '</button>'
            + '<button class="transparent-button delete-button-%button-id%">'
            + '<img class="control-button" src="assets/image/delete.png" alt="GCS" />'
            + '</button>'
            + '</div>'
            + '<a class="thumbnail thumbnail-%id%">'
            + '<img class="img-responsive %isDisable%" src="%url%" alt="GCS" />'
            + '<div class="center-text">%title%</div>' + '</a>' + '</div>',
    FIRST_PAGE_TEMPLATE : '<li id = "first-page" class="page-item">'
            + '<a class="page-link" aria-label="Previous">'
            + '<span aria-hidden="true">&laquo;</span>'
            + '<span class="sr-only">Previous</span></a>' + '</li>',
    PREVIOUS_PAGE_TEMPLATE : '<li id = "previous-page" class="page-item">'
            + ' <a class="page-link" aria-label="Previous">'
            + '<span aria-hidden="true">&lsaquo;</span> '
            + '<span class="sr-only">Previous</span></a>' + '</li>',
    NEXT_PAGE_TEMPLATE : '<li id = "next-page" class="page-item">'
            + '<a class="page-link" aria-labels="Next">'
            + '<span aria-hidden="true">&rsaquo;</span>'
            + '<span class="sr-only">Next</span></a> ' + '</li>',
    LAST_PAGE_TEMPLATE : '<li id = "last-page" class="page-item">'
            + '<a class="page-link" aria-labels="Next">'
            + '<span aria-hidden="true">&raquo;</span>'
            + '<span class="sr-only">Next</span></a>' + '</li>',
    PAGE_NUMBER_TEMPLATE : '<li class="%htmlClassName%%id%"><a class="page-link" href="#"> %id% </a></li>',

    NEW_ITEM_FORM_TEMPLATE : '<label for="itemType">Item Type</label>'
            + '<select id="item-type" name="item-type">' + '<option value='
            + CONSTANT.AREA_CODE + '>Area</option>' + '<option value='
            + CONSTANT.CAMERA_CODE + '>Camera</option>' + '</select>'
            + '<label for="name">Name</label>'
            + '<input type="text" id="add-popup-name" name="name">'
            + '<label for="status">Status</label>'
            + '<input type="text" id="add-popup-status" name="status">',
    EDIT_ITEM_FORM_TEMPLATE : '<label for="name">Name</label>'
            + '<input type="text" id="edit-popup-name" name="name">'
            + '<label for="status">Status</label>'
            + '<input type="text" id="edit-popup-status" name="status">',
    AREA_FORM_TEMPLATE : '<label for="desc">Description</label>'
            + '<input type="text" id="add-popup-desc" name="desc">',
    CAMERA_FORM_TEMPLATE : '<label for="url"> Stream URL</label>\
            <input type="text" id="add-popup-url" name="url">\
            <label for="cam-width">Camera Width</label>\
            <input type="text" id="add-popup-cam-width" name="cam-width">\
            <label for="cam-height">Camera Height</label>\
            <input type="text" id="add-popup-cam-height" name="cam-height">\
            <label >Camera Services</label><br>\
            <input type="checkbox" name="cam-service" value="2" checked> Motion Tracking\
            <input type="checkbox" name="cam-service" value="3"> Face Recognition\
            <input type="checkbox" name="cam-service" value="4"> Smoke Detection<br>',
    CONFIRM_FORM_TEMPLATE : '',
    ADD_IMAGE_TEMPLATE : '<label for="image">Image</label>'
            + '<form method="post" action="savefiles" modelAttribute="uploadForm" enctype="multipart/form-data">'
            + '<p>Select files to upload </p>' + '<table id="fileTable">'
            + '<tr>' + ' <td><input name="files[0]" type="file" /></td>'
            + ' <td><input type="submit" value="Upload" /></td>' + '</tr>'
            + '</table>' + '</form>',
    FILE_UPLOAD_TEMPLATE : '<label class="control-lable" for="file">Upload a file</label>'
            + '<input id="pop-up-fileupload" type="file" path="file" accept="image/gif, image/jpeg, image/png" class="form-control input-sm" name="files[]" data-url="/oa-web/upload/image"/>'
            + '<div id="progress" class="progress">'
            + '<div class="bar" style="width: 0%;"></div>' + '</div>',
    SHOW_POS_INFO_TEMPLATE : 
        '<div class="table-responsive">\
        <button id="pos-info-delete" class="transparent-button ">\
            Delete\
        </button>\
        <button id="pos-info-cancel" class="transparent-button ">\
            <img class="control-button" src="assets/image/delete.png" alt="GCS" />\
        </button>\
        <table class="table borderless">\
        <tbody>\
            <tr>\
                <td>Person Id</td>\
                <td>%pos-info-per-id%</td>\
            </tr>\
            <tr>\
                <td>Name</td>\
                <td>%pos-info-per-name%</td>\
            </tr>\
            <tr>\
                <td>Email</td>\
                <td>%pos-info-per-email%</td>\
            </tr>\
            <tr>\
                <td>Address</td>\
                <td>%pos-info-per-address%</td>\
            </tr>\
            <tr>\
                <td>Birthday</td>\
                <td>%pos-info-per-bday%</td>\
            </tr>\
            <tr>\
                <td>Phone</td>\
                <td>%pos-info-per-phone%</td>\
            </tr>\
        </tbody>\
        </table></div>',
        EDIT_PERSON_INFO_TEMPLATE:'<label for="name">Name</label>\
            <input type="text" id="edit-person-popup-name" name="name"/>\
            <label for="email">Email</label>\
            <input type="text" id="edit-person-popup-email" name="email"/>\
            <label for="address">Address</label>\
            <input type="text" id="edit-person-popup-address" name="address"/>\
            <label for="bday">Birth Day</label>\
            <input type="text" id="edit-person-popup-bdate" name="bdate"/>\
            <label for="phone">Phone</label>\
            <input type="text" id="edit-person-popup-phone" name="phone"/>',
    SEARCH_RESULT_TEMPLATE : '<div class="bottom-border">\
                                <table class="table borderless">\
                                <tr>\
                                      <td class = "col-md-2" rowspan="3">\
                                      <img class="img-responsive" style ="width:100px; height:100px;" src="%image%" />\
                                      </td>\
                                          <td class = "col-md-7"><div>\
                                          %label-name%: %name%\
                                      </div></td>\
                                      <td class = "col-md-3"><div>\
                                      %label-id%: %id%\
                                      </div></td>\
                                </tr>\
                                <tr>\
                                    <td class = "col-md-7"><div>\
                                        %label-location%: %location%\
                                    </div></td>\
                                    <td class = "col-md-3"><div>\
                                        <a href="%path%">%path-text%</a>\
                                    </div></td>\
                                </tr>\
                            </table></div>',
    SEARCH_AREA_RESULT_TEMPLATE : 
                      '<div class="bottom-border">\
                      <table class="table borderless">\
                          <tr>\
                                <td class = "col-md-2" rowspan="3">\
                                <img class="img-responsive" style ="width:100px; height:100px;" src="%image%" />\
                                </td>\
                                    <td class = "col-md-7"><div>\
                                    %label-name%: %name%\
                                </div></td>\
                                <td class = "col-md-3"><div>\
                                %label-id%: %id%\
                                </div></td>\
                          </tr>\
                          <tr>\
                                <td  class = "col-md-7"><div>\
                                      %label-desc%: %desc%\
                                </div></td>\
                          </tr>\
                          <tr>\
                              <td class = "col-md-7"><div>\
                                  %label-location%: %location%\
                              </div></td>\
                              <td class = "col-md-3"><div>\
                                  <a href="%path%">%path-text%</a>\
                              </div></td>\
                          </tr>\
                      </table></div>',
      SEARCH_ALL_TEMPLATE:' <div id="area-search-title">Area search result: </br></div>\
                            <div id="area-search-result"></div>\
                            <div id="person-search-title">Person search result: </br></div>\
                            <div id="person-search-result"></div>\
                            <div id="camera-search-title">Camera search result: </br></div>\
                            <div id="camera-search-result"></div>'
}