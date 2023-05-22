import { ITask } from '@/shared/model/task.model';
import { IProject } from '@/shared/model/project.model';

export interface IMoM {
  id?: number;
  title?: string;
  meetingDate?: Date | null;
  minutesWritingDate?: Date | null;
  meetingObjectif?: string | null;
  meetingDocReferences?: string | null;
  isMoMActionsClosed?: boolean | null;
  subjectDecisions?: string | null;
  meetingPhotoCaptureContentType?: string | null;
  meetingPhotoCapture?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  attendees?: string | null;
  notes?: string | null;
  tasks?: ITask[] | null;
  projet?: IProject | null;
}

export class MoM implements IMoM {
  constructor(
    public id?: number,
    public title?: string,
    public meetingDate?: Date | null,
    public minutesWritingDate?: Date | null,
    public meetingObjectif?: string | null,
    public meetingDocReferences?: string | null,
    public isMoMActionsClosed?: boolean | null,
    public subjectDecisions?: string | null,
    public meetingPhotoCaptureContentType?: string | null,
    public meetingPhotoCapture?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public attendees?: string | null,
    public notes?: string | null,
    public tasks?: ITask[] | null,
    public projet?: IProject | null
  ) {
    this.isMoMActionsClosed = this.isMoMActionsClosed ?? false;
  }
}
