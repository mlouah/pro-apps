import { ITask } from '@/shared/model/task.model';
import { IProject } from '@/shared/model/project.model';

export interface IMoM {
  id?: number;
  title?: string;
  meetingDate?: Date | null;
  docWritingDate?: Date | null;
  deadLine?: Date | null;
  meetingObjectif?: string | null;
  meetingDocReferences?: string | null;
  isMoMActionsClosed?: boolean | null;
  decisions?: string | null;
  attendees?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  meetingPhotoCaptureContentType?: string | null;
  meetingPhotoCapture?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
  projet?: IProject | null;
}

export class MoM implements IMoM {
  constructor(
    public id?: number,
    public title?: string,
    public meetingDate?: Date | null,
    public docWritingDate?: Date | null,
    public deadLine?: Date | null,
    public meetingObjectif?: string | null,
    public meetingDocReferences?: string | null,
    public isMoMActionsClosed?: boolean | null,
    public decisions?: string | null,
    public attendees?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public meetingPhotoCaptureContentType?: string | null,
    public meetingPhotoCapture?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null,
    public projet?: IProject | null
  ) {
    this.isMoMActionsClosed = this.isMoMActionsClosed ?? false;
  }
}
