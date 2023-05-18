/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from '@/shared/date/filters';
import MoMService from '@/entities/mo-m/mo-m.service';
import { MoM } from '@/shared/model/mo-m.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('MoM Service', () => {
    let service: MoMService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new MoMService();
      currentDate = new Date();
      elemDefault = new MoM(
        123,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            meetingDate: dayjs(currentDate).format(DATE_FORMAT),
            docWritingDate: dayjs(currentDate).format(DATE_FORMAT),
            deadLine: dayjs(currentDate).format(DATE_FORMAT),
            dateCreation: dayjs(currentDate).format(DATE_TIME_FORMAT),
            dateModify: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a MoM', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            meetingDate: dayjs(currentDate).format(DATE_FORMAT),
            docWritingDate: dayjs(currentDate).format(DATE_FORMAT),
            deadLine: dayjs(currentDate).format(DATE_FORMAT),
            dateCreation: dayjs(currentDate).format(DATE_TIME_FORMAT),
            dateModify: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            meetingDate: currentDate,
            docWritingDate: currentDate,
            deadLine: currentDate,
            dateCreation: currentDate,
            dateModify: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MoM', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MoM', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            meetingDate: dayjs(currentDate).format(DATE_FORMAT),
            docWritingDate: dayjs(currentDate).format(DATE_FORMAT),
            deadLine: dayjs(currentDate).format(DATE_FORMAT),
            meetingObjectif: 'BBBBBB',
            meetingDocReferences: 'BBBBBB',
            isMoMActionsClosed: true,
            decisions: 'BBBBBB',
            attendees: 'BBBBBB',
            dateCreation: dayjs(currentDate).format(DATE_TIME_FORMAT),
            dateModify: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifyBy: 'BBBBBB',
            createdBy: 'BBBBBB',
            meetingPhotoCapture: 'BBBBBB',
            notes: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            meetingDate: currentDate,
            docWritingDate: currentDate,
            deadLine: currentDate,
            dateCreation: currentDate,
            dateModify: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MoM', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MoM', async () => {
        const patchObject = Object.assign(
          {
            title: 'BBBBBB',
            meetingDate: dayjs(currentDate).format(DATE_FORMAT),
            meetingObjectif: 'BBBBBB',
            dateModify: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifyBy: 'BBBBBB',
            createdBy: 'BBBBBB',
          },
          new MoM()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            meetingDate: currentDate,
            docWritingDate: currentDate,
            deadLine: currentDate,
            dateCreation: currentDate,
            dateModify: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MoM', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MoM', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            meetingDate: dayjs(currentDate).format(DATE_FORMAT),
            docWritingDate: dayjs(currentDate).format(DATE_FORMAT),
            deadLine: dayjs(currentDate).format(DATE_FORMAT),
            meetingObjectif: 'BBBBBB',
            meetingDocReferences: 'BBBBBB',
            isMoMActionsClosed: true,
            decisions: 'BBBBBB',
            attendees: 'BBBBBB',
            dateCreation: dayjs(currentDate).format(DATE_TIME_FORMAT),
            dateModify: dayjs(currentDate).format(DATE_TIME_FORMAT),
            lastModifyBy: 'BBBBBB',
            createdBy: 'BBBBBB',
            meetingPhotoCapture: 'BBBBBB',
            notes: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            meetingDate: currentDate,
            docWritingDate: currentDate,
            deadLine: currentDate,
            dateCreation: currentDate,
            dateModify: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MoM', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MoM', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MoM', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
