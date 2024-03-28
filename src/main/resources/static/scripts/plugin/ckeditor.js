import Plugin from '@ckeditor/ckeditor5-core/src/plugin';
import ButtonView from '@ckeditor/ckeditor5-ui/src/button/buttonview';

export default class Alignment extends Plugin {
    init() {
        const editor = this.editor;

        // Thêm lệnh 'alignment' vào CKEditor
        editor.commands.add('alignment', {
            // Logic xử lý khi lệnh được kích hoạt
            exec() {
                console.log('Alignment command executed!');
            }
        });

        // Thêm nút vào thanh công cụ
        editor.ui.componentFactory.add('alignment', locale => {
            const view = new ButtonView(locale);

            // Thiết lập các thuộc tính của nút, ví dụ: icon, label, etc.
            view.set({
                label: 'Alignment',
                icon: 'alignmentIcon', // Thay 'alignmentIcon' bằng đường dẫn đến biểu tượng của bạn
                tooltip: true
            });

            // Xử lý sự kiện khi nút được nhấp
            view.on('execute', () => {
                // Kích hoạt lệnh 'alignment' khi nút được nhấp
                editor.execute('alignment');
            });

            return view;
        });
    }
}