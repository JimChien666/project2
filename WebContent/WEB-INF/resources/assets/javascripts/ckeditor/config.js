/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
//	config.extraPlugins = 'imgur';
	config.extraPlugins = 'autogrow,imgur';
    // Get your client-id from https://api.imgur.com/oauth2/addclient
    config.imgurClientID = '0a82c3db40fd99e';
	config.font_names = 'Arial;Arial Black;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;新細明體;細明體;標楷體;微軟正黑體';
	config.fontSize_sizes = '12/12px;13/13px;16/16px;15/15px;18/18px;20/20px;22/22px;24/24px;36/36px;48/48px;';
	config.height = '30em';
	
	
//	config.extraPlugins = 'autogrow';
	config.autoGrow_minHeight = 200;
	config.autoGrow_bottomSpace = 50;
	config.width = '80%';
	
//	config.autoGrow_maxHeight = 600;
	
	config.removeButtons = 'CreateDiv,Source,Save,Templates,NewPage,Preview,Print,Cut,Copy,Paste,PasteText,PasteFromWord,Find,Replace,Undo,Redo,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CopyFormatting,Language,BidiRtl,BidiLtr,Image,Flash,Table,HorizontalRule,SpecialChar,Iframe,About,ShowBlocks,Maximize,Anchor';
	// Dialog windows are also simplified.
	config.removeDialogTabs = 'link:advanced;image:Link;image:advanced';
	config.removePlugins = 'image';
	
};
