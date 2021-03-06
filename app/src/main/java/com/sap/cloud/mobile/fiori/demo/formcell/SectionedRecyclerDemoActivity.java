package com.sap.cloud.mobile.fiori.demo.formcell;

import static com.sap.cloud.mobile.fiori.demo.formcell.CustomFormCell1.CUSTOM_FORM_CELL_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sap.cloud.mobile.fiori.attachment.Attachment;
import com.sap.cloud.mobile.fiori.attachment.AttachmentFormCell;
import com.sap.cloud.mobile.fiori.attachment.AttachmentItemClickListener;
import com.sap.cloud.mobile.fiori.attachment.actions.AttachmentActionSelectFile;
import com.sap.cloud.mobile.fiori.demo.AbstractDemoActivity;
import com.sap.cloud.mobile.fiori.demo.R;
import com.sap.cloud.mobile.fiori.formcell.DateTimePickerFormCell;
import com.sap.cloud.mobile.fiori.formcell.Duration;
import com.sap.cloud.mobile.fiori.formcell.DurationPickerFormCell;
import com.sap.cloud.mobile.fiori.formcell.FormCell;
import com.sap.cloud.mobile.fiori.formcell.FormCellCreator;
import com.sap.cloud.mobile.fiori.formcell.NoteFormCell;
import com.sap.cloud.mobile.fiori.formcell.SectionedRecyclerViewAdapter;
import com.sap.cloud.mobile.fiori.formcell.SeparatorFormCell;
import com.sap.cloud.mobile.fiori.formcell.SliderFormCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectionedRecyclerDemoActivity extends AbstractDemoActivity {

    @NonNull
    ArrayList<Attachment> selectedAttachment1 = new ArrayList<>();
    @NonNull
    ArrayList<Attachment> selectedAttachment2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectioned_recycler_demo);
        RecyclerView recyclerView = findViewById(R.id.sectionedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        SectionedRecyclerViewAdapter adapter = new SectionedRecyclerViewAdapter() {

            @NonNull
            @Override
            public FormCellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                FormCellHolder holder = super.onCreateViewHolder(parent, viewType);

                if (viewType == FormCell.WidgetType.ATTACHMENT.ordinal()) {
                    AttachmentFormCell cell = (AttachmentFormCell) holder.itemView;
                    cell.setEditable(true);
                    cell.addAttachmentActions(Collections.singletonList(
                            new AttachmentActionSelectFile("Select Files", cell)));
                    return new FormCellHolder(cell);
                }
                return holder;
            }

            @Override
            public int getItemCountForSection(int section) {
                switch (section) {
                    case 0:
                        return 2;
                    case 1:
                        return 3;
                    case 2:
                        return 3;
                    case 3:
                        return 2;
                    case 4:
                        return 2;
                    case 5:
                        return 2;
                    case 6:
                        return 2;
                    default:
                        return 30;
                }
            }


            @Override
            public void onBindCellHolder(@NonNull FormCellHolder cellHolder, int section, int row) {
                switch (section) {
                    case 0:
                        switch (row) {
                            case 0:
                                AttachmentFormCell mAttachmentFormCell = (AttachmentFormCell) cellHolder.itemView;
                                mAttachmentFormCell.clear();
                                mAttachmentFormCell.setValue(selectedAttachment1);
                                mAttachmentFormCell.setCellValueChangeListener(new FormCell.CellValueChangeListener<List<Attachment>>() {
                                    @Override
                                    public void cellChangeHandler(List<Attachment> value) {

                                        for (Attachment attachment : value) {
                                            if (!selectedAttachment1.contains(attachment)) {
                                                selectedAttachment1.add(attachment);
                                            }
                                        }
                                        mAttachmentFormCell.setKey(String.format(getString(R.string.attachment_default_key), mAttachmentFormCell.getValue().size()));
                                    }
                                });
                                if (!mAttachmentFormCell.getAttachmentItemClickListeners().isEmpty()) {
                                    mAttachmentFormCell.getAttachmentItemClickListeners().clear();
                                }
                                mAttachmentFormCell.addAttachmentTouchListener(new AttachmentItemClickListener() {
                                    @Override
                                    public void onClick(View view, int position) {

                                    }

                                    @Override
                                    public void onLongClick(View view, int position) {

                                    }

                                    @Override
                                    public void onClickDelete(Attachment attachment) {
                                        selectedAttachment1.remove(attachment);
                                    }
                                });
                                break;

                            default:
                                AttachmentFormCell mAttachmentFormCell2 = (AttachmentFormCell) cellHolder.itemView;
                                mAttachmentFormCell2.clear();
                                mAttachmentFormCell2.setValue(selectedAttachment2);
                                mAttachmentFormCell2.setCellValueChangeListener(new FormCell.CellValueChangeListener<List<Attachment>>() {
                                    @Override
                                    public void cellChangeHandler(List<Attachment> value) {
                                        for (Attachment attachment : value) {
                                            if (!selectedAttachment2.contains(attachment)) {
                                                selectedAttachment2.add(attachment);
                                            }
                                        }
                                        mAttachmentFormCell2.setKey(String.format(getString(R.string.attachment_default_key), mAttachmentFormCell2.getValue().size()));
                                    }
                                });
                                if (!mAttachmentFormCell2.getAttachmentItemClickListeners().isEmpty()) {
                                    mAttachmentFormCell2.getAttachmentItemClickListeners().clear();
                                }
                                mAttachmentFormCell2.addAttachmentTouchListener(new AttachmentItemClickListener() {
                                    @Override
                                    public void onClick(View view, int position) {

                                    }

                                    @Override
                                    public void onLongClick(View view, int position) {

                                    }

                                    @Override
                                    public void onClickDelete(Attachment attachment) {
                                        selectedAttachment2.remove(attachment);
                                    }
                                });
                                break;
                        }
                        break;
                    case 1:
                        NoteFormCell noteFormCell = (NoteFormCell) cellHolder.itemView;
                        noteFormCell.setValue("Note: " + section + " and row: " + row);
                        break;
                    case 2:
                        DurationPickerFormCell durationPickerFormCell = (DurationPickerFormCell) cellHolder.itemView;
                        durationPickerFormCell.setKey("Duration picker: " + section + " and row: " + row);
                        durationPickerFormCell.setValue(new Duration(section * row, row));
                        break;
                    case 3:
                        SliderFormCell slider = (SliderFormCell) cellHolder.itemView;
                        slider.setKey("Slider: " + section + " and row: " + row);
                        slider.setValue(section * row * 10);
                        break;
                    case 4:
                        DateTimePickerFormCell dateTimePickerFormCell = (DateTimePickerFormCell) cellHolder.itemView;
                        dateTimePickerFormCell.setKey("Date time picker: " + section + " and row: " + row);
                        break;
                    case 5:
                    case 6:
                        CustomFormCell1 customFormCell1 = (CustomFormCell1) cellHolder.itemView;
                        customFormCell1.setValue("CustomView: " + section + " and row: " + row);
                        break;
                    default:
                        NoteFormCell noteFormCell1 = (NoteFormCell) cellHolder.itemView;
                        noteFormCell1.setValue("Note: " + section + " and row: " + row);
                        break;
                }
            }

            @Override
            public int getItemViewType(int section, int row) {
                switch (section) {
                    case 0:
                        return FormCell.WidgetType.ATTACHMENT.ordinal();
                    case 1:
                        return FormCell.WidgetType.NOTE.ordinal();
                    case 2:
                        return FormCell.WidgetType.DURATION_PICKER.ordinal();
                    case 3:
                        return FormCell.WidgetType.SLIDER.ordinal();
                    case 4:
                        return FormCell.WidgetType.DATE_TIME_PICKER.ordinal();
                    case 5:
                    case 6:
                        return CUSTOM_FORM_CELL_1;
                    default:
                        return FormCell.WidgetType.NOTE.ordinal();
                }
            }

            @Override
            public int getNumberOfSections() {
                return 8;
            }

            @Override
            protected void onBindHeader(@NonNull FormCellHolder header, int section) {
                ((SectionHeaderFooter) header.itemView).setValue("Header For Section: " + section);
                ((SectionHeaderFooter) header.itemView).setTextColor(getColor(R.color.sap_ui_formcell_key));
            }

            @Override
            protected void onBindFooter(@NonNull FormCellHolder footer, int section) {
                ((SectionHeaderFooter) footer.itemView).setValue("Footer For Section: " + section);
                ((SectionHeaderFooter) footer.itemView).setTextColor(getColor(R.color.sap_ui_base_color));
            }

            /*
            protected void onBindSeparatorViewHolder(@NonNull SectionedRecyclerViewAdapter.FormCellHolder holder, int section) {
                SeparatorFormCell separatorFormCell = ((SeparatorFormCell) holder.itemView);
                separatorFormCell.setCaption("Captioned Separator");
                separatorFormCell.setTextColor(getResources().getColor(R.color.white, getBaseContext().getTheme()));
            }
            */
        };

        adapter.registerCellCreator(CUSTOM_FORM_CELL_1, new FormCellCreator() {
            @NonNull
            @Override
            public View onCreateCell(@NonNull ViewGroup parent) {
                return new CustomFormCell1(parent.getContext());
            }
        });
        adapter.registerCellCreator(FormCell.WidgetType.Separator.ordinal(), new FormCellCreator() {
            @NonNull
            @Override
            public View onCreateCell(@NonNull ViewGroup parent) {
                return new CustomSeparator(parent.getContext());
            }
        });

        adapter.setFooterEnabled(true);
        adapter.setHeaderEnabled(true);
        adapter.setSeparatorEnabled(true);


        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AttachmentFormCell.onReceiveSelection(requestCode, resultCode, data, getBaseContext());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("attachments1", selectedAttachment1);
        outState.putParcelableArrayList("attachments2", selectedAttachment2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<Attachment> attachments = savedInstanceState.getParcelableArrayList("attachments1");
        if (attachments != null) {
            selectedAttachment1.addAll(attachments);
        }

        attachments = savedInstanceState.getParcelableArrayList("attachments2");
        if (attachments != null) {
            selectedAttachment2.addAll(attachments);
        }
    }
}


class CustomSeparator extends SeparatorFormCell {

    public CustomSeparator(Context context) {
        this(context, null);
    }

    public CustomSeparator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int padding = (int) getResources().getDimension(R.dimen.demo_separator_margin);
        setPadding(2 * padding, padding, 2 * padding, padding);
        setBackgroundColor(getResources().getColor(R.color.sap_ui_base_color, getContext().getTheme()));
    }
}
